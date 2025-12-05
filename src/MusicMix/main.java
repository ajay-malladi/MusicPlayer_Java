package MusicMix;

import java.io.IOException;
import javax.sound.sampled.*;
import java.io.File;
import java.util.*;

public class main {

    //MusicPlayer Program  done using classes--> File,Clip, AudioSystem


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String path = "src\\Music";
        int choice = 0;
        boolean flag = true;
        boolean rope = true;

        File folder = new File(path);   // stores the reference of folder
        File[] list = folder.listFiles((dir, name) -> name.endsWith(".wav"));  // listing the files into folder
        //(dir, name) -> name.endsWith(".wav")  lambda expresion

        int index = 0;


        try {    //Because clip dies when the try block ends.
            Clip clip = AudioSystem.getClip();//It ONLY creates an empty audio tank.
            loadClip(clip, list[index]);   // initially loaded with audio


            while (flag) {
                System.out.println();
                System.out.println("🎵🎵🎵MUSIC_PLAYER🎵🎵🎵");
                System.out.println("Pick Your Choice: ");
                System.out.println("1. Play     🎵");
                System.out.println("2. next     ⏭️");
                System.out.println("3. Replay   🔄️");
                System.out.println("4. stop     ✋ ");
                System.out.println("5. quit     👋");
                System.out.print("Enter choice : ");
                choice = scanner.nextInt();
                scanner.nextLine();


                switch (choice) {
                    case 1 -> {       //  ->1   === wrong
                        System.out.println("🎵🎵" + list[index].getName());
                        clip.start();


                    }
                    case 2 -> {
                        if (!clip.isRunning()) {
                            System.out.println("🎵🎵" + list[index].getName());
                            clip.start();
                            continue;
                        }

                        if (clip.isRunning()) {
                            clip.stop();
                            clip.close();
                        }

                        index++;
                        if (index >= (list.length)) {
                            index = 0;
                            loadClip(clip, list[index]);
                            System.out.println("🎵🎵" + list[index].getName());
                            clip.start();
                            Thread.sleep(4000);
                            continue;   // if we want to skip after this line
                        }

                        loadClip(clip, list[index]);
                        System.out.println("🎵🎵" + list[index].getName());
                        clip.start();


                    }
                    case 3 -> {
                        clip.setMicrosecondPosition(0);
                        System.out.println("🎵🎵" + list[index].getName());
                        clip.start();

                    }


                    case 4 -> {
                        if (!clip.isRunning()) {
                            System.out.println("Select Play Song !!");
                            Thread.sleep(4000);
                            continue;
                        }
                        clip.stop();
                        System.out.println("PAUSED ⏸️");
                    }
                    case 5 -> {
                        clip.stop();
                        clip.close();
                        System.out.println("😎Play Music After Sometime !!");
                    }
                    default -> {
                        System.out.println("InValid Choice !!");
                    }


                }
                if (choice == 5) {
                    flag = false;
                }
                Thread.sleep(4000);
            }

        } catch (
                LineUnavailableException e) {
            System.out.println("line Unavailable");
        } catch (
                InterruptedException e) {
            System.out.println("InterruptedException");
        } catch (Exception e) {
            System.out.println("Something went wrong !!");
            System.exit(0);
        } finally {
            assert list != null;
            System.out.println();
            System.out.println("MUSIC FILES ");
            for (File file : list) {
                System.out.println(file);
            }

        }


    }

    static void loadClip(Clip clip, File path) {
        try (AudioInputStream audio = AudioSystem.getAudioInputStream(path);) {
            clip.open(audio);
            // loads the audio data


        } catch (UnsupportedAudioFileException e) {
            System.out.println("unsupported audio file Exception");
        } catch (IOException e) {
            System.out.println("input error");
        } catch (LineUnavailableException e) {
            System.out.println("unavailable error");
        }


    }
}

//System.out.println(list[1].getName());
//System.out.println("loaded");
//System.out.println(list.length);
