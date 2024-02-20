package priv.samera2022.module.gadgets.mc.modpack;

import priv.samera2022.module.gadgets.mc.modpack.download.Concept;
import priv.samera2022.module.gadgets.mc.modpack.download.JsonReader;
import priv.samera2022.module.file.ZipHandler;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ModpackHandler {
    public static void main(String[] args) {
        String zipPath = "D:\\_S_A_M\\Desktop\\A - Copy.zip"; // ZIP文件路径

    }

    public static void handle(String zipPath, String target, String type){
        String rawName = new File(zipPath).getName();
        String name = rawName.substring(0,rawName.lastIndexOf("."));
        String manifest = ZipHandler.readFromZip(zipPath, "manifest.json");
        HashMap<String,Object> map = JsonReader.readFJ(manifest);
        String packName = (String) map.get(Concept.NAME);
        String path = target+File.separator+packName+"("+name+")";
        File folder = new File(path);
        if (!folder.exists()) {
            try {
                ZipHandler.extract(zipPath, "overrides", path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        switch (type){
            case "key":
                priv.samera2022.module.gadgets.mc.modpack.download.key.Download.downloadModpacks(zipPath, path+"\\mods");
                break;
            case "browser":
                priv.samera2022.module.gadgets.mc.modpack.download.browser.Download.downloadModpacks(zipPath);
                break;
            default:
                break;
        }
    }
}
