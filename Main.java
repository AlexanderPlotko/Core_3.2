package sohranenieProgrammyi;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        GameProgress game1 = new GameProgress(100, 2, 8, 9.5);
        GameProgress game2 = new GameProgress(54, 4, 10, 19.5);
        GameProgress game3 = new GameProgress(25, 9, 18, 69.5);


        saveGame("c://Games/savegames/game1.dat", game1);
        saveGame("c://Games/savegames/game2.dat", game2);
        saveGame("c://Games/savegames/game3.dat", game3);

        List<String> list = new ArrayList<>();
        list.add("c://Games/savegames/game1.dat");
        list.add("c://Games/savegames/game2.dat");
        list.add("c://Games/savegames/game3.dat");

        zipFiles("c://Games/savegames/zip.zip", list);

        new File("c://Games/savegames/game1.dat").delete(); //удаляем сохранялки
        new File("c://Games/savegames/game2.dat").delete();
        new File("c://Games/savegames/game3.dat").delete();
    }

    private static void saveGame(String path, GameProgress game) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(game);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void zipFiles(String path, List<String> list) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(path))) {
            int i = 1;
            for (String way : list) {
                try (FileInputStream fis = new FileInputStream(way)) {
                    ZipEntry entry = new ZipEntry("Save" + i);
                    i++;
                    zout.putNextEntry(entry);
                    byte[] bytes = new byte[fis.available()]; //разбиваем на байты
                    fis.read(bytes);
                    zout.write(bytes);
                    zout.closeEntry();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
