package guohuayu.com.iweather.data.http.entity;

import java.util.List;

/**
 * Created by Administrator on 2018/12/13.
 */

public class WeatherForecastEntity {

    /**
     * citycode : 101190101
     * rdesc : Success
     * forecast : [{"wind":{"dir":"东风","deg":"74","sc":"3-4","spd":"11"},"hum":"94","astro":{"mr":"19:57","sr":"06:35","ms":"09:07","ss":"17:04"},"pcpn":"11.0","uv":"2","tmp":{"min":"14","max":"16"},"pop":"100","pres":"1020","date":"2016-11-17","cond":{"cond_n":"小雨","cond_d":"阴"},"vis":"2"},{"wind":{"dir":"西南风","deg":"272","sc":"3-4","spd":"12"},"hum":"93","astro":{"mr":"20:58","sr":"06:36","ms":"10:06","ss":"17:03"},"pcpn":"8.0","uv":"2","tmp":{"min":"16","max":"19"},"pop":"79","pres":"1015","date":"2016-11-18","cond":{"cond_n":"小雨","cond_d":"小雨"},"vis":"10"},{"wind":{"dir":"北风","deg":"331","sc":"微风","spd":"3"},"hum":"88","astro":{"mr":"21:59","sr":"06:37","ms":"10:59","ss":"17:03"},"pcpn":"0.1","uv":"3","tmp":{"min":"15","max":"20"},"pop":"4","pres":"1016","date":"2016-11-19","cond":{"cond_n":"阴","cond_d":"阴"},"vis":"10"},{"wind":{"dir":"东北风","deg":"75","sc":"4-5","spd":"17"},"hum":"93","astro":{"mr":"22:59","sr":"06:38","ms":"11:46","ss":"17:02"},"pcpn":"4.4","uv":"1","tmp":{"min":"14","max":"17"},"pop":"82","pres":"1020","date":"2016-11-20","cond":{"cond_n":"小雨","cond_d":"小雨"},"vis":"2"},{"wind":{"dir":"东风","deg":"90","sc":"4-5","spd":"24"},"hum":"93","astro":{"mr":"23:57","sr":"06:39","ms":"12:27","ss":"17:02"},"pcpn":"24.2","uv":"N/A","tmp":{"min":"12","max":"16"},"pop":"81","pres":"1018","date":"2016-11-21","cond":{"cond_n":"小雨","cond_d":"小雨"},"vis":"2"},{"wind":{"dir":"北风","deg":"28","sc":"5-6","spd":"29"},"hum":"91","astro":{"mr":"null","sr":"06:40","ms":"13:05","ss":"17:02"},"pcpn":"10.7","uv":"N/A","tmp":{"min":"5","max":"16"},"pop":"83","pres":"1025","date":"2016-11-22","cond":{"cond_n":"小雨","cond_d":"阴"},"vis":"9"},{"wind":{"dir":"北风","deg":"27","sc":"4-5","spd":"24"},"hum":"86","astro":{"mr":"00:53","sr":"06:41","ms":"13:40","ss":"17:01"},"pcpn":"8.8","uv":"N/A","tmp":{"min":"3","max":"12"},"pop":"82","pres":"1032","date":"2016-11-23","cond":{"cond_n":"小雨","cond_d":"小雨"},"vis":"10"}]
     * rcode : 200
     * suggestion : {"trav":{"brf":"适宜","txt":"天气较好，风稍大，但温度适宜，总体来说还是好天气。这样的天气适宜旅游，您可以尽情享受大自然的风光。"},"uv":{"brf":"最弱","txt":"属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。"},"flu":{"brf":"少发","txt":"各项气象条件适宜，无明显降温过程，发生感冒机率较低。"},"comf":{"brf":"舒适","txt":"白天不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。"},"sport":{"brf":"较适宜","txt":"阴天，较适宜进行各种户内外运动。"},"air":{"brf":"良","txt":"气象条件有利于空气污染物稀释、扩散和清除，可在室外正常活动。"},"cw":{"brf":"较适宜","txt":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"},"drs":{"brf":"较舒适","txt":"建议着薄外套、开衫牛仔衫裤等服装。年老体弱者应适当添加衣物，宜着夹克衫、薄毛衣等。"}}
     * cityname : 南京
     */

    private String citycode;
    private String rdesc;
    private int rcode;
    private SuggestionBean suggestion;
    private String cityname;
    private List<ForecastBean> forecast;

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getRdesc() {
        return rdesc;
    }

    public void setRdesc(String rdesc) {
        this.rdesc = rdesc;
    }

    public int getRcode() {
        return rcode;
    }

    public void setRcode(int rcode) {
        this.rcode = rcode;
    }

    public SuggestionBean getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(SuggestionBean suggestion) {
        this.suggestion = suggestion;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public List<ForecastBean> getForecast() {
        return forecast;
    }

    public void setForecast(List<ForecastBean> forecast) {
        this.forecast = forecast;
    }

    public static class SuggestionBean {
        /**
         * trav : {"brf":"适宜","txt":"天气较好，风稍大，但温度适宜，总体来说还是好天气。这样的天气适宜旅游，您可以尽情享受大自然的风光。"}
         * uv : {"brf":"最弱","txt":"属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。"}
         * flu : {"brf":"少发","txt":"各项气象条件适宜，无明显降温过程，发生感冒机率较低。"}
         * comf : {"brf":"舒适","txt":"白天不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。"}
         * sport : {"brf":"较适宜","txt":"阴天，较适宜进行各种户内外运动。"}
         * air : {"brf":"良","txt":"气象条件有利于空气污染物稀释、扩散和清除，可在室外正常活动。"}
         * cw : {"brf":"较适宜","txt":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"}
         * drs : {"brf":"较舒适","txt":"建议着薄外套、开衫牛仔衫裤等服装。年老体弱者应适当添加衣物，宜着夹克衫、薄毛衣等。"}
         */

        private TravBean trav;
        private UvBean uv;
        private FluBean flu;
        private ComfBean comf;
        private SportBean sport;
        private AirBean air;
        private CwBean cw;
        private DrsBean drs;

        public TravBean getTrav() {
            return trav;
        }

        public void setTrav(TravBean trav) {
            this.trav = trav;
        }

        public UvBean getUv() {
            return uv;
        }

        public void setUv(UvBean uv) {
            this.uv = uv;
        }

        public FluBean getFlu() {
            return flu;
        }

        public void setFlu(FluBean flu) {
            this.flu = flu;
        }

        public ComfBean getComf() {
            return comf;
        }

        public void setComf(ComfBean comf) {
            this.comf = comf;
        }

        public SportBean getSport() {
            return sport;
        }

        public void setSport(SportBean sport) {
            this.sport = sport;
        }

        public AirBean getAir() {
            return air;
        }

        public void setAir(AirBean air) {
            this.air = air;
        }

        public CwBean getCw() {
            return cw;
        }

        public void setCw(CwBean cw) {
            this.cw = cw;
        }

        public DrsBean getDrs() {
            return drs;
        }

        public void setDrs(DrsBean drs) {
            this.drs = drs;
        }

        public static class TravBean {
            /**
             * brf : 适宜
             * txt : 天气较好，风稍大，但温度适宜，总体来说还是好天气。这样的天气适宜旅游，您可以尽情享受大自然的风光。
             */

            private String brf;
            private String txt;

            public String getBrf() {
                return brf;
            }

            public void setBrf(String brf) {
                this.brf = brf;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }
        }

        public static class UvBean {
            /**
             * brf : 最弱
             * txt : 属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。
             */

            private String brf;
            private String txt;

            public String getBrf() {
                return brf;
            }

            public void setBrf(String brf) {
                this.brf = brf;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }
        }

        public static class FluBean {
            /**
             * brf : 少发
             * txt : 各项气象条件适宜，无明显降温过程，发生感冒机率较低。
             */

            private String brf;
            private String txt;

            public String getBrf() {
                return brf;
            }

            public void setBrf(String brf) {
                this.brf = brf;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }
        }

        public static class ComfBean {
            /**
             * brf : 舒适
             * txt : 白天不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。
             */

            private String brf;
            private String txt;

            public String getBrf() {
                return brf;
            }

            public void setBrf(String brf) {
                this.brf = brf;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }
        }

        public static class SportBean {
            /**
             * brf : 较适宜
             * txt : 阴天，较适宜进行各种户内外运动。
             */

            private String brf;
            private String txt;

            public String getBrf() {
                return brf;
            }

            public void setBrf(String brf) {
                this.brf = brf;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }
        }

        public static class AirBean {
            /**
             * brf : 良
             * txt : 气象条件有利于空气污染物稀释、扩散和清除，可在室外正常活动。
             */

            private String brf;
            private String txt;

            public String getBrf() {
                return brf;
            }

            public void setBrf(String brf) {
                this.brf = brf;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }
        }

        public static class CwBean {
            /**
             * brf : 较适宜
             * txt : 较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。
             */

            private String brf;
            private String txt;

            public String getBrf() {
                return brf;
            }

            public void setBrf(String brf) {
                this.brf = brf;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }
        }

        public static class DrsBean {
            /**
             * brf : 较舒适
             * txt : 建议着薄外套、开衫牛仔衫裤等服装。年老体弱者应适当添加衣物，宜着夹克衫、薄毛衣等。
             */

            private String brf;
            private String txt;

            public String getBrf() {
                return brf;
            }

            public void setBrf(String brf) {
                this.brf = brf;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }
        }
    }

    public static class ForecastBean {
        /**
         * wind : {"dir":"东风","deg":"74","sc":"3-4","spd":"11"}
         * hum : 94
         * astro : {"mr":"19:57","sr":"06:35","ms":"09:07","ss":"17:04"}
         * pcpn : 11.0
         * uv : 2
         * tmp : {"min":"14","max":"16"}
         * pop : 100
         * pres : 1020
         * date : 2016-11-17
         * cond : {"cond_n":"小雨","cond_d":"阴"}
         * vis : 2
         */

        private WindBean wind;//风力风向数据
        private String hum;//相对湿度(%)
        private AstroBean astro;//天文数据
        private String pcpn;//降水量(mm)
        private String uv;//紫外线级别
        private TmpBean tmp;//气温 当天最高和最低
        private String pop;//降水概率(%)
        private String pres;//气压(hPa)
        private String date;
        private CondBean cond;//	天气现象
        private String vis;//能见度(km)

        public WindBean getWind() {
            return wind;
        }

        public void setWind(WindBean wind) {
            this.wind = wind;
        }

        public String getHum() {
            return hum;
        }

        public void setHum(String hum) {
            this.hum = hum;
        }

        public AstroBean getAstro() {
            return astro;
        }

        public void setAstro(AstroBean astro) {
            this.astro = astro;
        }

        public String getPcpn() {
            return pcpn;
        }

        public void setPcpn(String pcpn) {
            this.pcpn = pcpn;
        }

        public String getUv() {
            return uv;
        }

        public void setUv(String uv) {
            this.uv = uv;
        }

        public TmpBean getTmp() {
            return tmp;
        }

        public void setTmp(TmpBean tmp) {
            this.tmp = tmp;
        }

        public String getPop() {
            return pop;
        }

        public void setPop(String pop) {
            this.pop = pop;
        }

        public String getPres() {
            return pres;
        }

        public void setPres(String pres) {
            this.pres = pres;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public CondBean getCond() {
            return cond;
        }

        public void setCond(CondBean cond) {
            this.cond = cond;
        }

        public String getVis() {
            return vis;
        }

        public void setVis(String vis) {
            this.vis = vis;
        }

        public static class WindBean {
            /**
             * dir : 东风
             * deg : 74
             * sc : 3-4
             * spd : 11
             */

            private String dir;
            private String deg;
            private String sc;
            private String spd;

            public String getDir() {
                return dir;
            }

            public void setDir(String dir) {
                this.dir = dir;
            }

            public String getDeg() {
                return deg;
            }

            public void setDeg(String deg) {
                this.deg = deg;
            }

            public String getSc() {
                return sc;
            }

            public void setSc(String sc) {
                this.sc = sc;
            }

            public String getSpd() {
                return spd;
            }

            public void setSpd(String spd) {
                this.spd = spd;
            }
        }

        public static class AstroBean {
            /**
             * mr : 19:57
             * sr : 06:35
             * ms : 09:07
             * ss : 17:04
             */

            private String mr;
            private String sr;
            private String ms;
            private String ss;

            public String getMr() {
                return mr;
            }

            public void setMr(String mr) {
                this.mr = mr;
            }

            public String getSr() {
                return sr;
            }

            public void setSr(String sr) {
                this.sr = sr;
            }

            public String getMs() {
                return ms;
            }

            public void setMs(String ms) {
                this.ms = ms;
            }

            public String getSs() {
                return ss;
            }

            public void setSs(String ss) {
                this.ss = ss;
            }
        }

        public static class TmpBean {
            /**
             * min : 14
             * max : 16
             */

            private String min;
            private String max;

            public String getMin() {
                return min;
            }

            public void setMin(String min) {
                this.min = min;
            }

            public String getMax() {
                return max;
            }

            public void setMax(String max) {
                this.max = max;
            }
        }

        public static class CondBean {
            /**
             * cond_n : 小雨
             * cond_d : 阴
             */

            private String cond_n;
            private String cond_d;

            public String getCond_n() {
                return cond_n;
            }

            public void setCond_n(String cond_n) {
                this.cond_n = cond_n;
            }

            public String getCond_d() {
                return cond_d;
            }

            public void setCond_d(String cond_d) {
                this.cond_d = cond_d;
            }
        }
    }
}
