package service;

import com.example.bot.spring.echo.entity.Page;

//下載頁面
public interface IDownLoadService {
    public Page download(String url);

}
