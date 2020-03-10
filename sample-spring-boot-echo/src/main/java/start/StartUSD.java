package start;

import com.example.bot.spring.echo.entity.Page;
import lombok.Data;
import service.IDownLoadService;
import service.IProcessService;
import service.Impl.HttpClientDownLoadService;
import service.Impl.TWBank;

@Data
public class StartUSD {
    private IDownLoadService downLoadService;
    private IProcessService  processService;
    public static void main(String[] args){
        StartUSD usd = new StartUSD();
        usd.setDownLoadService(new HttpClientDownLoadService());
        usd.setProcessService(new TWBank());
        String url = "https://rate.bot.com.tw/xrt?Lang=zh-TW";
        //下載頁面
        Page page=usd.downLoadPage(url);
        usd.processPage(page);
        //System.out.print(page.getContent());
    }

    //下載頁面
    public Page downLoadPage(String url){
        return this.downLoadService.download(url);
    }
    //解析頁面
    public void processPage(Page page){
        this.processService.process(page);
    }
}
