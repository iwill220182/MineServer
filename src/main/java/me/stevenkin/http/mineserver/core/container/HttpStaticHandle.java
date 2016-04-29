package me.stevenkin.http.mineserver.core.container;

import java.io.File;
import java.util.List;

import me.stevenkin.http.mineserver.core.entry.HttpRequest;
import me.stevenkin.http.mineserver.core.entry.HttpResponse;
import me.stevenkin.http.mineserver.core.exception.NoFoundException;
import me.stevenkin.http.mineserver.core.util.ConfigUtil;
import me.stevenkin.http.mineserver.core.util.FileUtil;

/**
 * Created by wjg on 16-4-26.
 */
public class HttpStaticHandle extends AbstractHandle {
    private String basePath = ConfigUtil.getConfig("basePath","/home/wjg/server");

    @Override
    public void service(HttpRequest request, HttpResponse response) throws Exception {
        String path = ((List<String>)request.getAttribute("matcherStrList")).get(0);
        String file;
        int index = path.indexOf("?");
        file = path.substring(0,index<0?path.length():index);
        String filePath = basePath+file;
        File f = new File(filePath);
        if(!f.exists()){
            throw new NoFoundException();
        }
        byte[] body = FileUtil.getFileContent(f);
        response.getOutput().write(body);
    }
}