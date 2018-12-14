package com.example.namp5.jsonhttpurlconnection;

import java.io.Serializable;

/**
 * Created by namp5 on 12/14/2018.
 */
    public class Users implements Serializable {
        private String mLanguage;
        private String mName;
        private Owner mOwner;

        public Users(String language, String name) {
            this.mLanguage = language;
            this.mName = name;
        }

        public String getmLanguage() {
            return mLanguage;
        }

        public String getmName() {
            return mName;
        }


        public Owner getmOwner() {
            return mOwner;
        }

        public void setmOwner(Owner owner) {
            this.mOwner = owner;
        }
}
