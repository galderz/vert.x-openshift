package example;

import io.vertx.core.Future;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.ext.web.Router;

import java.util.logging.Level;
import java.util.logging.Logger;

public class VertxExample extends AbstractVerticle {

   private static final Logger log = Logger.getLogger(VertxExample.class.getName());

   @Override
   public void start(Future<Void> future) {
      Router router = Router.router(vertx);
      router.get("/").handler(rc -> {
         rc.response().end("Hej världen!");
      });

      vertx.createHttpServer()
         .requestHandler(router::accept)
         .rxListen(8080)
         .subscribe(
            server -> {
               log.info("HTTP server started");
               future.complete();
            },
            future::fail
         );

//         .listen(8080,
//            result -> {
//               if (result.succeeded()) {
//                  log.info("HTTP server started");
//                  future.complete();
//               } else {
//                  future.fail(result.cause());
//               }
//            }
//         );

   }

   public static void main(final String... args) {
      Vertx vertx = Vertx.vertx();
      vertx.deployVerticle(VertxExample.class.getName(), new DeploymentOptions());
   }

}
