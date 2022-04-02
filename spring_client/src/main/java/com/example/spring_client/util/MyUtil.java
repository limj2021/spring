package com.example.spring_client.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

//util功能包，针对本项目工具类--设置为组件
@Component
public class MyUtil {

    //生成小于28位的可控制的长度的随机id---字符串
    public static String getUUID(int digit){
        //取绝对值
        int value = Math.abs(digit);
        //生成32位uuid
        UUID uuid=UUID.randomUUID();
        //转化成字符串
        String str = uuid.toString();
        //将其中的-变成空--好像一般都是一个-
        String uuidStr=str.replace("-", "");
        //拆解为字符串
        char[] a=uuidStr.toCharArray();
        //建立一个新数组
        StringBuilder strdigit = new StringBuilder();
        for (int i = 0; i < value; i++){
            //将拆解的字符加入到新数组
            strdigit.append(a[i]);
        }
        System.out.println(strdigit);
        String s = strdigit.toString();
        return s;
    }

    //生成可控制的长度的随机id---纯数字
    public static int getNum(int digit) {
        //取绝对值
        int value = Math.abs(digit);
        //建立数组
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < value; i++) {
            if (value > 1){
                //会存在1-9
                str.append(new Random().nextInt(9) + 1);
            }
            else{
                //digit=1--会存在0-9
                str.append(new Random().nextInt(10));
            }
        }
        Integer num = Integer.valueOf(str.toString());
        System.out.println(num);
        return num;
    }
//    获取当前时间
    public static String currentTime(){
        Date date = new Date();
        SimpleDateFormat matter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format_date = matter.format(date);
        return format_date;
    }

/*    public static void main(String[] args) {
        System.out.println(getPinyin("zhongguohong123")); //--zhongguohong123
    }*/
    //暂时废弃，因为需要的是拼音转汉字，不是汉字转拼音
    /**
     * @param china (字符串 汉字)
     * @return 汉字转拼音 其它字符不变
     */
    public static String getPinyin(String china){
        HanyuPinyinOutputFormat formart = new HanyuPinyinOutputFormat();
        formart.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        formart.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        formart.setVCharType(HanyuPinyinVCharType.WITH_V);
        char[] arrays = china.trim().toCharArray();
        String result = "";
        try {
            for (int i=0;i<arrays.length;i++) {
                char ti = arrays[i];
                if(Character.toString(ti).matches("[\\u4e00-\\u9fa5]")){ //匹配是否是中文
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(ti,formart);
                    result += temp[0];
                }else{
                    result += ti;
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }

        return result;
    }


}
