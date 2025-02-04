import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        List<String> inputFiles = new ArrayList<>();
        boolean argsO = false;
        String outPath = "";
        boolean argsP = false;
        String intName = "integers.txt";
        String stringName = "strings.txt";
        String floatName = "floats.txt";
        boolean argsA = false;
        boolean argsS = false;
        boolean argsF = false;

        for(int i = 0; i < args.length; i++){

            if(args[i].equals("-o")){
                argsO = true;
                if(++i < args.length) {
                    outPath = args[i] + "/";
                }
            }else if(args[i].equals("-p") && !argsP){
                argsP = true;
                if(++i < args.length){
                    intName = args[i] + intName;
                    stringName = args[i] + stringName;
                    floatName = args[i] + floatName;
                }
            }else if(args[i].equals("-a")){
                argsA = true;
            }else if(args[i].equals("-s")){
                argsS = true;
            }else if(args[i].equals("-f")){
                argsF = true;
            }else{
                inputFiles.add(args[i]);
            }
        }

        List<Scanner> scanFile = new ArrayList<>();
        for (String inputFile : inputFiles) {
            scanFile.add(new Scanner(new File(inputFile)));
        }

        List<String> allToStr = new ArrayList<>();
        while(scanFile.size()>0){
            for(int i = 0; i < scanFile.size(); i++) {
                if (scanFile.get(i).hasNext()) {
                    allToStr.add(scanFile.get(i).nextLine());
                } else {
                    scanFile.get(i).close();
                    scanFile.remove(i);
                }
            }
        }

        List<BigInteger> allInts = new ArrayList<>();
        List<String> allStr = new ArrayList<>();
        List<BigDecimal> allFloats = new ArrayList<>();

        int k = 0;
        while (k < allToStr.size()) {
            String s = allToStr.get(k++);
            try{
                allInts.add(new BigInteger(s));
            } catch (Exception e) {
                try{
                    allFloats.add(new BigDecimal(s));
                }catch (Exception a){
                    allStr.add(s);
                }
            }
        }

        if(!allInts.isEmpty()){
            try{
                FileWriter fw = new FileWriter(outPath+intName, argsA);
                for(BigInteger bigInteger : allInts){
                    fw.write(bigInteger+"\n");
                }
                fw.close();
            } catch (IOException e) {
                System.out.println("Ошибка записи числа");
            }
        }

        if(!allStr.isEmpty()){
            try{
                FileWriter fw = new FileWriter(outPath+stringName, argsA);
                for(String st : allStr){
                    fw.write(st+"\n");
                }
                fw.close();
            } catch (IOException e) {
                System.out.println(stringName);
                System.out.println("Ошибка записи строки");
            }
        }

        if(!allFloats.isEmpty()){
            try{
                FileWriter fw = new FileWriter(outPath+floatName, argsA);
                for (BigDecimal bigDecimal : allFloats) {
                    fw.write(bigDecimal + "\n");
                }
                fw.close();
            } catch (IOException e) {
                System.out.println("Ошибка записи вещественного числа");
            }
        }

        if(argsS){
            shortStat(allInts, allStr, allFloats);
        }

        if(argsF){
            fullStat(allInts, allStr, allFloats);
        }

    }

    public static void shortStat(List<BigInteger> allInts, List<String> allStr, List<BigDecimal> allFloats){

        if(!allInts.isEmpty()){
            System.out.println("    Кол-во целых чисел: " + allInts.size());
        }
        if(!allFloats.isEmpty()){
            System.out.println("    Кол-во вещественных чисел: " + allFloats.size());
        }
        if(!allStr.isEmpty()){
            System.out.println("    Кол-во строк: " + allStr.size());
        }
    }

    public static void fullStat(List<BigInteger> allInts, List<String> allStr, List<BigDecimal> allFloats){

        if(!allInts.isEmpty()){
            System.out.println("Целые числа:");
            Collections.sort(allInts);
            System.out.println("    Кол-во целых чисел: " + allInts.size());
            System.out.println("    Минимальное целое число: " + allInts.get(0));
            System.out.println("    Максимальное целое число: " + allInts.get(allInts.size()-1));
            BigInteger sum = new BigInteger("0");
            for (BigInteger bigInteger : allInts) {
                sum = sum.add(bigInteger);
            }
            System.out.println("    Сумма целых чисел: " + sum);
            try{
                BigDecimal avg = new BigDecimal(sum).divide(new BigDecimal(allInts.size()+""));
                System.out.println("    Cреднее арифетическое целых чисел: " + avg);
            }catch (Exception e){
                BigDecimal avg = new BigDecimal(sum).divide(new BigDecimal(allInts.size()+""),5, RoundingMode.HALF_UP);
                System.out.println("    Cреднее арифетическое целых чисел: " + avg + "  (округлено вверх до 5 зн. после запятой)");
            }
        }
        if(!allFloats.isEmpty()){
            System.out.println("Вещественные числа:");
            System.out.println("    Кол-во вещественных чисел: " + allFloats.size());
            Collections.sort(allFloats);
            System.out.println("    Минимальное вещественное число: " + allFloats.get(0));
            System.out.println("    Максимальное вещественное число: " + allFloats.get(allFloats.size()-1));
            BigDecimal sum = new BigDecimal(0);
            for (BigDecimal bigDecimal : allFloats) {
                sum = sum.add(bigDecimal);
            }
            System.out.println("    Сумма вещественных чисел: " + sum);
            try{
                BigDecimal avg = sum.divide(new BigDecimal(allFloats.size()+""));
                System.out.println("    Cреднее арифетическое вещественных чисел: " + avg);
            }catch (Exception e){
                BigDecimal avg = sum.divide(new BigDecimal(allFloats.size()+""),5, RoundingMode.HALF_UP);
                System.out.println("    Cреднее арифетическое вещественных чисел: " + avg + "  (округлено вверх до 5 зн. после запятой)");
            }
        }
        if(!allStr.isEmpty()){
            System.out.println("Строки:");
            System.out.println("    Кол-во строк: " + allStr.size());
            int min = allStr.get(0).length();
            int max = allStr.get(0).length();
            for(int i = 1; i < allStr.size(); i++){
                min = Math.min(min, allStr.get(i).length());
                max = Math.max(max, allStr.get(i).length());
            }
            System.out.println("    Минимальная длина строки: " + min);
            System.out.println("    Максимальная длина строки: " + max);
        }
    }
}
