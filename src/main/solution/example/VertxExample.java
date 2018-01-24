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
            failure -> {
               log.log(Level.SEVERE, "HTTP server failed to start", failure);
               future.fail(failure);
            }
         );
   }

}
