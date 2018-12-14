package com.example.namp5.jsonhttpurlconnection;

import java.io.Serializable;

/**
 * Created by namp5 on 12/14/2018.
 */

public class Owner implements Serializable {
    private String mAvatar;
    private String mId;

    public Owner(String avatar, String id) {
        this.mAvatar = avatar;
        this.mId = id;
    }

    public String getmAvatar() {
        return mAvatar;
    }

    public void setmAvatar(String avatar) {
        this.mAvatar = avatar;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String id) {
        this.mId = id;
    }
}
