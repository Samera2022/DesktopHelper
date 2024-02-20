package priv.samera2022.module.gadgets.mc.modpack.download.browser;

import priv.samera2022.module.gadgets.mc.modpack.download.Concept;
import priv.samera2022.module.gadgets.mc.modpack.download.JsonReader;
import priv.samera2022.module.file.ZipHandler;
import priv.samera2022.module.gadgets.mc.modpack.download.browser.type.Mod;
import priv.samera2022.module.mainFrame;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

public class Download {
    public static void main(String[] args) {
//        String loc = "D:\\_S_A_M\\Desktop\\A.zip";
//        downloadModpacks(loc);
        String str = "[\n" +
                "  {\n" +
                "    projectID=238222,\n" +
                "    required=true,\n" +
                "    fileID=3040523\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=283377,\n" +
                "    required=true,\n" +
                "    fileID=3366534\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=296676,\n" +
                "    required=true,\n" +
                "    fileID=2937109\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=229090,\n" +
                "    required=true,\n" +
                "    fileID=2682824\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=250702,\n" +
                "    required=true,\n" +
                "    fileID=3299405\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=233258,\n" +
                "    required=true,\n" +
                "    fileID=2810152\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=291429,\n" +
                "    required=true,\n" +
                "    fileID=2814421\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=243788,\n" +
                "    required=true,\n" +
                "    fileID=3157722\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=351264,\n" +
                "    required=true,\n" +
                "    fileID=3768446\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=254317,\n" +
                "    required=true,\n" +
                "    fileID=2932048\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=230497,\n" +
                "    required=true,\n" +
                "    fileID=2450900\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=253211,\n" +
                "    required=true,\n" +
                "    fileID=3308160\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=223852,\n" +
                "    required=true,\n" +
                "    fileID=2952606\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=295319,\n" +
                "    required=true,\n" +
                "    fileID=2865090\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=296430,\n" +
                "    required=true,\n" +
                "    fileID=2849124\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=248787,\n" +
                "    required=true,\n" +
                "    fileID=2987247\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=238551,\n" +
                "    required=true,\n" +
                "    fileID=2744766\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=237167,\n" +
                "    required=true,\n" +
                "    fileID=2985811\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=269024,\n" +
                "    required=true,\n" +
                "    fileID=2861574\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=410295,\n" +
                "    required=true,\n" +
                "    fileID=3593191\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=310029,\n" +
                "    required=true,\n" +
                "    fileID=2898177\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=245211,\n" +
                "    required=true,\n" +
                "    fileID=2667280\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=492574,\n" +
                "    required=true,\n" +
                "    fileID=3345703\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=298408,\n" +
                "    required=true,\n" +
                "    fileID=3039432\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=401178,\n" +
                "    required=true,\n" +
                "    fileID=3668460\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=327775,\n" +
                "    required=true,\n" +
                "    fileID=3119496\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=580404,\n" +
                "    required=true,\n" +
                "    fileID=3755364\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=454372,\n" +
                "    required=true,\n" +
                "    fileID=3773944\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=224472,\n" +
                "    required=true,\n" +
                "    fileID=2969118\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=309516,\n" +
                "    required=true,\n" +
                "    fileID=3570157\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=278494,\n" +
                "    required=true,\n" +
                "    fileID=3327893\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=493723,\n" +
                "    required=true,\n" +
                "    fileID=3651010\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=348025,\n" +
                "    required=true,\n" +
                "    fileID=3755305\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=233071,\n" +
                "    required=true,\n" +
                "    fileID=2562139\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=438332,\n" +
                "    required=true,\n" +
                "    fileID=3666797\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=357178,\n" +
                "    required=true,\n" +
                "    fileID=3437402\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=367706,\n" +
                "    required=true,\n" +
                "    fileID=3792698\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=225605,\n" +
                "    required=true,\n" +
                "    fileID=2489549\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=384885,\n" +
                "    required=true,\n" +
                "    fileID=3040796\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=226447,\n" +
                "    required=true,\n" +
                "    fileID=2477566\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=266890,\n" +
                "    required=true,\n" +
                "    fileID=3169485\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=255257,\n" +
                "    required=true,\n" +
                "    fileID=3507866\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=32274,\n" +
                "    required=true,\n" +
                "    fileID=2916002\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=239197,\n" +
                "    required=true,\n" +
                "    fileID=3784010\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=238372,\n" +
                "    required=true,\n" +
                "    fileID=2595310\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=367178,\n" +
                "    required=true,\n" +
                "    fileID=3520536\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=285742,\n" +
                "    required=true,\n" +
                "    fileID=3783384\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=250290,\n" +
                "    required=true,\n" +
                "    fileID=2665849\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=260262,\n" +
                "    required=true,\n" +
                "    fileID=3459767\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=270789,\n" +
                "    required=true,\n" +
                "    fileID=2920436\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=242818,\n" +
                "    required=true,\n" +
                "    fileID=2779848\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=408195,\n" +
                "    required=true,\n" +
                "    fileID=3152093\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=296843,\n" +
                "    required=true,\n" +
                "    fileID=2603356\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=297404,\n" +
                "    required=true,\n" +
                "    fileID=2669338\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=238403,\n" +
                "    required=true,\n" +
                "    fileID=2443194\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=419286,\n" +
                "    required=true,\n" +
                "    fileID=3687785\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=220954,\n" +
                "    required=true,\n" +
                "    fileID=3488553\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=280510,\n" +
                "    required=true,\n" +
                "    fileID=2643711\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=246996,\n" +
                "    required=true,\n" +
                "    fileID=3440963\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=225946,\n" +
                "    required=true,\n" +
                "    fileID=2680883\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=289380,\n" +
                "    required=true,\n" +
                "    fileID=2916310\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=291874,\n" +
                "    required=true,\n" +
                "    fileID=2799213\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=604054,\n" +
                "    required=true,\n" +
                "    fileID=3782777\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=240630,\n" +
                "    required=true,\n" +
                "    fileID=2728585\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=240633,\n" +
                "    required=true,\n" +
                "    fileID=2546769\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=225957,\n" +
                "    required=true,\n" +
                "    fileID=2499443\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=257814,\n" +
                "    required=true,\n" +
                "    fileID=3626833\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=78320,\n" +
                "    required=true,\n" +
                "    fileID=2627678\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=373051,\n" +
                "    required=true,\n" +
                "    fileID=3773760\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=309344,\n" +
                "    required=true,\n" +
                "    fileID=3337921\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=342584,\n" +
                "    required=true,\n" +
                "    fileID=3533131\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=227083,\n" +
                "    required=true,\n" +
                "    fileID=2518667\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=235670,\n" +
                "    required=true,\n" +
                "    fileID=3015688\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=289412,\n" +
                "    required=true,\n" +
                "    fileID=3156637\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=276951,\n" +
                "    required=true,\n" +
                "    fileID=2880613\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=286924,\n" +
                "    required=true,\n" +
                "    fileID=3695974\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=237065,\n" +
                "    required=true,\n" +
                "    fileID=3331364\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=268643,\n" +
                "    required=true,\n" +
                "    fileID=2573789\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=228756,\n" +
                "    required=true,\n" +
                "    fileID=2747935\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=314904,\n" +
                "    required=true,\n" +
                "    fileID=2819669\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=224602,\n" +
                "    required=true,\n" +
                "    fileID=3293318\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=285550,\n" +
                "    required=true,\n" +
                "    fileID=2962840\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=286660,\n" +
                "    required=true,\n" +
                "    fileID=2967287\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=229876,\n" +
                "    required=true,\n" +
                "    fileID=2483393\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=306886,\n" +
                "    required=true,\n" +
                "    fileID=2742537\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=244844,\n" +
                "    required=true,\n" +
                "    fileID=3505633\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=60089,\n" +
                "    required=true,\n" +
                "    fileID=2671937\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=244849,\n" +
                "    required=true,\n" +
                "    fileID=3554966\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=229060,\n" +
                "    required=true,\n" +
                "    fileID=2801262\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=248453,\n" +
                "    required=true,\n" +
                "    fileID=2785465\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=271998,\n" +
                "    required=true,\n" +
                "    fileID=2533316\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=244023,\n" +
                "    required=true,\n" +
                "    fileID=2463886\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=309674,\n" +
                "    required=true,\n" +
                "    fileID=3003364\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=237102,\n" +
                "    required=true,\n" +
                "    fileID=3157548\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=250398,\n" +
                "    required=true,\n" +
                "    fileID=3025548\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=315773,\n" +
                "    required=true,\n" +
                "    fileID=3341793\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=298323,\n" +
                "    required=true,\n" +
                "    fileID=2849447\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=302479,\n" +
                "    required=true,\n" +
                "    fileID=2783947\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=243478,\n" +
                "    required=true,\n" +
                "    fileID=2745657\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=265917,\n" +
                "    required=true,\n" +
                "    fileID=2951731\n" +
                "  },\n" +
                "  {\n" +
                "    projectID=284754,\n" +
                "    required=true,\n" +
                "    fileID=2725062\n" +
                "  }\n" +
                "]";
        System.out.println(count(str,'}'));
    }

    public static void downloadModpacks(String path) {
        String modlist = ZipHandler.readFromZip(path, "modlist.html");
        String manifest = ZipHandler.readFromZip(path, "manifest.json");
        int mods = count(modlist, '\"') / 2;
        if (mods >= 78) {
            int type = JOptionPane.showConfirmDialog(null, "模组过多，是否继续以这种方式下载？继续以这种方式下载可能有崩溃风险，且耗时约为" + Math.floorDiv(7 * mods, 60) + "分钟！", "模组过多警示！共有" + mods + "个模组！", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            mainFrame.logger.warn("模组过多，可能存在崩溃风险！");
            if (type == JOptionPane.YES_OPTION) {
                mainFrame.logger.info("一共有" + mods + "个mod，预计下载时间约为" + Math.floorDiv(7 * mods, 60) + "分钟");
                download(modlist, asMods((ArrayList<HashMap<String, Object>>) JsonReader.readFJ(manifest).get(Concept.FILES)), true);
            }
        }
    }

    public static int getNum(String path) {
        String modlist = ZipHandler.readFromZip(path, "modlist.html");
        return count(modlist, '\"') / 2;
    }

    private static int count(String str, char targetChar) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == targetChar) {
                count++;
            }
        }
        return count;
    }

    private static ArrayList<Mod> asMods(ArrayList<HashMap<String, Object>> maps) {
        ArrayList<Mod> mods = new ArrayList<>();
        for (HashMap<String, Object> map : maps) {
            Mod mod = new Mod();
            mod.setProjectID(Integer.parseInt((String) map.get(Concept.PROJECT_ID)));
            mod.setFileID(Integer.parseInt((String) map.get(Concept.FILE_ID)));
            mod.setRequired(Boolean.parseBoolean((String) map.get(Concept.REQUIRED)));
            mods.add(mod);
        }
        return mods;
    }

    //dOM就是downloadOptionalMods
    //返回值即为包含所有可选mod和必选mod的HashMap<String,ArrayList<String>>
    private static HashMap<String, ArrayList<String>> download(String html, ArrayList<Mod> mods, boolean dOM) {
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        ArrayList<String> necessary = new ArrayList<>();
        ArrayList<String> optional = new ArrayList<>();
        int time = -1;
        while (html.contains("\"")) {
            time++;
            Mod mod = mods.get(time);
            mainFrame.logger.info("正在下载id为" + mod.getFileID() + "的模组文件......");
            int firstIndexOf = html.indexOf("\"");
            html = html.substring(firstIndexOf + 1);
            String url = html.substring(0, html.indexOf("\"")) + "/download/" + mod.getFileID();
            if (mod.isRequired()) {
                necessary.add(url);
                openUrl(url);
            } else {
                optional.add(url);
                if (dOM) openUrl(url);
            }
            try {
                Thread.sleep(7000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
                mainFrame.ExceptionHandler(e);
            }
            System.out.println(url);
            html = html.substring(html.indexOf("\"") + 1);
        }
        map.put("necessary", necessary);
        map.put("optional", optional);
        return map;
    }

    private static void openUrl(String url) {
        // 检查当前平台是否支持Desktop类
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            // 检查是否支持打开浏览器
            if (desktop.isSupported(Desktop.Action.BROWSE)) {
                try {
                    // 创建URI对象
                    URI uri = new URI(url);

                    // 调用默认浏览器打开网页
                    desktop.browse(uri);
                } catch (IOException | URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
