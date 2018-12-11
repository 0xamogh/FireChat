package amoghjapps.com.snapchatclone;

import android.net.Uri;

public class ModelImage {
    String id,caption,loginid;
    Uri imagelink;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    public Uri getImagelink() {
        return imagelink;
    }

    public void setImagelink(Uri imagelink) {
        this.imagelink = imagelink;
    }

    public ModelImage(String id, String caption, String loginid, Uri imagelink) {
        this.id = id;
        this.caption = caption;
        this.loginid = loginid;
        this.imagelink = imagelink;
    }
}
