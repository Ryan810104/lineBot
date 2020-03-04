package service.Impl;

import com.example.bot.spring.echo.Test;
import entity.Page;
import service.IDownLoadService;

public class HttpClientDownLoadService implements IDownLoadService {
    @Override
    public Page download(String url) {
        Page page = new Page();
        page.setContent(Test.Crawler(url));
        return page;
    }
}
