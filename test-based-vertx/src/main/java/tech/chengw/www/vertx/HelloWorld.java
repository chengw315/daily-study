package tech.chengw.www.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.MultiMap;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/11/11
 **/
public class HelloWorld {

    public static void main(String[] args) {
        Vertx.vertx().deployVerticle(new MainVerticle());
    }

    static class MainVerticle extends AbstractVerticle {
        @Override
        public void start() throws Exception {
            Router router = Router.router(vertx);
            router.route().handler(context -> {
                String address = context.request().connection().remoteAddress().toString();
                MultiMap queryParams = context.queryParams();
                String name = queryParams.contains("name") ? queryParams.get("name") : "unFound";
                context.json(new JsonObject()
                        .put("address",address)
                        .put("params",queryParams)
                        .put("name",name));
            });
            vertx.createHttpServer()
                    .requestHandler(router)
                    .listen(8080)
                    .onSuccess(httpServer -> {
                        System.out.println("vertx success start,port:"+ httpServer.actualPort());
                    });
        }
    }
}
