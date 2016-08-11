package com.godD.appwidget.bean;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.Table;

import java.io.Serializable;
import java.util.List;

/**
 * @author David  create on 2016/8/10  19:26.
 * @email david.forever.god@gmail.com
 * Learn from yesterday, live for today, hope for tomorrow.
 */
public class WidgetEntity implements Serializable {
    /**
     * code : 200
     * msg : success
     * newslist : [{"ctime":"2016-03-06 14:11","title":"Beautyleg &#8211; Arvi 私房美腿写真","description":"美女图片","picUrl":"http://m.xxxiao.com/wp-content/uploads/sites/3/2015/05/m.xxxiao.com_e7e731faf790487ccaf90d11774fae6b-760x500.jpg","url":"http://m.xxxiao.com/1353"},{"ctime":"2016-03-06 14:11","title":"美少女照片Archer小清新私拍","description":"美女图片","picUrl":"http://m.xxxiao.com/wp-content/uploads/sites/3/2015/04/m.xxxiao.com_dd10e91fcb9dd240038ae0bf7609944e-760x500.jpg","url":"http://m.xxxiao.com/146"},{"ctime":"2016-03-06 14:11","title":"[TGOD推女神] 泳池美人虞 75F乳神于姬Una私房泳装","description":"美女图片","picUrl":"http://m.xxxiao.com/wp-content/uploads/sites/3/2015/04/m.xxxiao.com_3e6ffd8abf2d3fbd1040b02edb6bcb66-760x500.jpg","url":"http://m.xxxiao.com/370"},{"ctime":"2016-03-06 14:11","title":"俏皮泳装巨乳美女杉原杏璃","description":"美女图片","picUrl":"http://m.xxxiao.com/wp-content/uploads/sites/3/2015/06/m.xxxiao.com_4458cc69f8353d1ab287f6ba830c4edb-760x500.jpg","url":"http://m.xxxiao.com/1939"},{"ctime":"2016-03-06 14:11","title":"中国乳神峰起樊玲","description":"美女图片","picUrl":"http://m.xxxiao.com/wp-content/uploads/sites/3/2015/07/m.xxxiao.com_ed8dedecf4d4a62f60528676f6649b85-760x500.jpg","url":"http://m.xxxiao.com/2187"},{"ctime":"2016-03-06 14:11","title":"巨乳杉原杏璃 Anri Sugihara 写真集 Gravure Idols &#038; Misty A","description":"美女图片","picUrl":"http://m.xxxiao.com/wp-content/uploads/sites/3/2015/06/m.xxxiao.com_8dc731bdd7da6e0362c7ddaf8ee0e813-760x500.jpg","url":"http://m.xxxiao.com/1531"},{"ctime":"2016-03-06 14:11","title":"韩国性感车模许允美人体艺术沙龙写真","description":"美女图片","picUrl":"http://m.xxxiao.com/wp-content/uploads/sites/3/2015/05/m.xxxiao.com_26dc4928630738bf68fa09f8b0d93d221-760x500.jpg","url":"http://m.xxxiao.com/1411"},{"ctime":"2016-03-06 14:11","title":"长发美女齐贝贝大秀性感火辣身姿","description":"美女图片","picUrl":"http://t1.27270.com/uploads/tu/201507/375/slt.jpg","url":"http://www.27270.com/ent/meinvtupian/2015/122489.html"},{"ctime":"2016-03-06 14:11","title":"混血美女尽展清新可人靓照","description":"美女图片","picUrl":"http://t1.27270.com/uploads/150727/8-150HGG21ME.jpg","url":"http://www.27270.com/ent/meinvtupian/2015/49617.html"},{"ctime":"2016-03-06 14:11","title":"嫩模刘嘉琦白色衬衫丁字裤展千娇百媚魅力","description":"美女图片","picUrl":"http://t1.27270.com/uploads/tu/201507/391/slt.jpg","url":"http://www.27270.com/ent/meinvtupian/2015/122766.html"}]
     */
    private static final long serialVersionUID = -7060210544600464481L;
    private int code;
    private String msg;


    private List<NewslistBean> newslist;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<NewslistBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewslistBean> newslist) {
        this.newslist = newslist;
    }

    @Table(name = "info")
    public static class NewslistBean {
        @Id(column = "id")
        private int id;
        private String ctime;
        @Column(column = "title")
        private String title;
        private String description;
        @Column(column = "picurl")
        private String picUrl;
        @Column(column = "url")
        private String url;

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    @Override
    public String toString() {
        return "Entity{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", newslist=" + newslist +
                '}';
    }
}
