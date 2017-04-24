package com.gz.hkjs.app.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/16.
 */

public class RecipesDetail {
    /**
     * ret : 200
     * msg :
     * data : {"id":"1","name":"健身好食谱","logo_url":"http://alipic.cnys.com/upload9/image/2017-03-21/CJf7GAbayH.jpg","dsc":"排骨软嫩多汁，汤汁浓郁鲜美，口感丰富，咬上一口，味蕾被迅速打开，让人回味绵长！","energy":"1000","use_time":"0","exaggerate":"0","step":[{"name":"肉洗净后切成方块","logo_url":"http://alipic.cnys.com/upload9/image/2017-03-21/PTNNGG4Ath.jpg","dsc":"我们今天要准备的食材有排骨700g、葱、姜、白酒、番茄酱、蚝油、韩式辣酱、生菜、料酒适量。","px_num":"1"},{"name":"锅中放入热水","logo_url":"http://alipic.cnys.com/upload9/image/2017-03-21/jaineExrXx.jpg","dsc":"首先将排骨砍成块、葱切段、姜切成姜丝。锅中放入热水，水开后，加入几滴白酒，将排骨下锅，焯水至变色后，捞出备用。","px_num":"2"},{"name":"锅中放入油","logo_url":"http://alipic.cnys.com/upload9/image/2017-03-21/BaBRdMDTfd.jpg","dsc":"锅中放入油，下入姜丝、葱段，煸炒数秒，然后将排骨倒入锅中翻炒，待排骨表面微黄时，加入适量番茄酱、适量蚝油均匀翻炒。","px_num":"3"},{"name":"翻炒","logo_url":"http://alipic.cnys.com/upload9/image/2017-03-21/SnJzk2x56b.jpg","dsc":"接着加入韩式辣酱，翻炒至香味溢出时，淋入适量料酒调味，倒入清水，以盖过排骨二分之一为宜，焖三十分钟，待排骨软烂，汤汁逐渐收干后就可以出锅了。","px_num":"4"}],"ingredients":[{"name":"大葱","num":"少量"},{"name":"蒜","num":"少量"},{"name":"肉","num":"2斤"},{"name":"糖","num":"3勺"}]}
     */

    private int ret;
    private String msg;
    private DataBean data;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * name : 健身好食谱
         * logo_url : http://alipic.cnys.com/upload9/image/2017-03-21/CJf7GAbayH.jpg
         * dsc : 排骨软嫩多汁，汤汁浓郁鲜美，口感丰富，咬上一口，味蕾被迅速打开，让人回味绵长！
         * energy : 1000
         * use_time : 0
         * exaggerate : 0
         * step : [{"name":"肉洗净后切成方块","logo_url":"http://alipic.cnys.com/upload9/image/2017-03-21/PTNNGG4Ath.jpg","dsc":"我们今天要准备的食材有排骨700g、葱、姜、白酒、番茄酱、蚝油、韩式辣酱、生菜、料酒适量。","px_num":"1"},{"name":"锅中放入热水","logo_url":"http://alipic.cnys.com/upload9/image/2017-03-21/jaineExrXx.jpg","dsc":"首先将排骨砍成块、葱切段、姜切成姜丝。锅中放入热水，水开后，加入几滴白酒，将排骨下锅，焯水至变色后，捞出备用。","px_num":"2"},{"name":"锅中放入油","logo_url":"http://alipic.cnys.com/upload9/image/2017-03-21/BaBRdMDTfd.jpg","dsc":"锅中放入油，下入姜丝、葱段，煸炒数秒，然后将排骨倒入锅中翻炒，待排骨表面微黄时，加入适量番茄酱、适量蚝油均匀翻炒。","px_num":"3"},{"name":"翻炒","logo_url":"http://alipic.cnys.com/upload9/image/2017-03-21/SnJzk2x56b.jpg","dsc":"接着加入韩式辣酱，翻炒至香味溢出时，淋入适量料酒调味，倒入清水，以盖过排骨二分之一为宜，焖三十分钟，待排骨软烂，汤汁逐渐收干后就可以出锅了。","px_num":"4"}]
         * ingredients : [{"name":"大葱","num":"少量"},{"name":"蒜","num":"少量"},{"name":"肉","num":"2斤"},{"name":"糖","num":"3勺"}]
         */

        private String id;
        private String name;
        private String logo_url;
        private String dsc;
        private String energy;
        private String use_time;
        private String exaggerate;
        private List<StepBean> step;
        private List<IngredientsBean> ingredients;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLogo_url() {
            return logo_url;
        }

        public void setLogo_url(String logo_url) {
            this.logo_url = logo_url;
        }

        public String getDsc() {
            return dsc;
        }

        public void setDsc(String dsc) {
            this.dsc = dsc;
        }

        public String getEnergy() {
            return energy;
        }

        public void setEnergy(String energy) {
            this.energy = energy;
        }

        public String getUse_time() {
            return use_time;
        }

        public void setUse_time(String use_time) {
            this.use_time = use_time;
        }

        public String getExaggerate() {
            return exaggerate;
        }

        public void setExaggerate(String exaggerate) {
            this.exaggerate = exaggerate;
        }

        public List<StepBean> getStep() {
            return step;
        }

        public void setStep(List<StepBean> step) {
            this.step = step;
        }

        public List<IngredientsBean> getIngredients() {
            return ingredients;
        }

        public void setIngredients(List<IngredientsBean> ingredients) {
            this.ingredients = ingredients;
        }

        public static class StepBean {
            /**
             * name : 肉洗净后切成方块
             * logo_url : http://alipic.cnys.com/upload9/image/2017-03-21/PTNNGG4Ath.jpg
             * dsc : 我们今天要准备的食材有排骨700g、葱、姜、白酒、番茄酱、蚝油、韩式辣酱、生菜、料酒适量。
             * px_num : 1
             */

            private String name;
            private String logo_url;
            private String dsc;
            private String px_num;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getLogo_url() {
                return logo_url;
            }

            public void setLogo_url(String logo_url) {
                this.logo_url = logo_url;
            }

            public String getDsc() {
                return dsc;
            }

            public void setDsc(String dsc) {
                this.dsc = dsc;
            }

            public String getPx_num() {
                return px_num;
            }

            public void setPx_num(String px_num) {
                this.px_num = px_num;
            }
        }

        public static class IngredientsBean {
            /**
             * name : 大葱
             * num : 少量
             */

            private String name;
            private String num;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }
        }
    }
}
