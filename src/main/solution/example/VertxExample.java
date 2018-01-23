package example;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.ext.web.Router;

public class VertxExample extends AbstractVerticle {

   @Override
   public void start(Future<Void> future) {
      Router router = Router.router(vertx);
      router.get("/").handler(rc -> {
         rc.response().end("Hej världen!");
      });

      vertx.createHttpServer()
         .requestHandler(router::accept)
         .listen(8080, result -> {
            System.out.println("Http server started");
            future.complete();
         });
   }

}
