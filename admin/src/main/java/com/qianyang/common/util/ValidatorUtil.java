package com.qianyang.common.util;


import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

public class ValidatorUtil {

    public static void main(String[] args) throws Exception {
        System.out.println(isNotIDCard("34220119950123771x"));
        System.out.println(isMax("上海",128));
    }

    /**
     * 判断字符串是否相等
     *
     * @param str1
     *            需要验证的字符串1
     * @param str2
     *            需要验证的字符串2
     * @return true|false 是或否
     */
    public static boolean isEquals(String str1, String str2) {
        return StringUtils.equals(str1, str2);
    }

    /**
     * 判断字符串是否不相等
     *
     * @param str1
     *            需要验证的字符串1
     * @param str2
     *            需要验证的字符串2
     * @return true|false 是或否
     */
    public static boolean isNotEquals(String str1, String str2) {
        return !StringUtils.equals(str1, str2);
    }

    /**
     * 判断字符串是否为空
     *
     * @param str
     *            需要验证的字符串
     * @return true|false 是或否
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    /**
     * 判断对象是否为NULL
     *
     * @param obj
     *            需要验证的对象
     * @return true|false 是或否
     */
    public static boolean isEmpty(Object obj) {
        return obj == null;
    }

    /**
     * 判断集合对象是否为NULL
     *
     * @param list
     *            需要验证的集合对象
     * @return true|false 是或否
     */
    public static boolean isEmpty(List<?> list) {
        return !(list != null && list.size() > 0);
    }

    /**
     * 判断字符串是否不为空
     *
     * @param str
     *            需要验证的字符串
     * @return true|false 是或否
     */
    public static boolean isNotEmpty(String str) {
        return str != null && str.trim().length() > 0;
    }

    /**
     * 判断对象是否不为NULL
     *
     * @param obj
     *            需要验证的对象
     * @return true|false 是或否
     */
    public static boolean isNotEmpty(Object obj) {
        return obj != null;
    }

    /**
     * 判断集合对象是否不为NULL
     *
     * @param list
     *            需要验证的集合对象
     * @return true|false 是或否
     */
    public static boolean isNotEmpty(List<?> list) {
        return list != null && list.size() > 0;
    }

    /**
     * 判断是整数
     *
     * @param str
     *            传入的字符串
     * @return true|false是整数返回true,否则返回false
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * 判断不是整数
     *
     * @param str
     *            传入的字符串
     * @return true|false是整数返回true,否则返回false
     */
    public static boolean isNotInteger(String str) {
        return isInteger(str) ? false : true;
    }

    /**
     * 判断是数字表示
     *
     * @param str
     *            源字符串
     * @return true|false是否数字的标志
     */
    public static boolean isNumeric(String str) {
        boolean return_value = false;
        if (str != null && str.length() > 0) {
            Matcher m = Pattern.compile("^[0-9\\-]+$").matcher(str);
            if (m.find()) {
                return_value = true;
            }
        }
        return return_value;
    }

    /**
     * 判断不是数字表示
     *
     * @param str
     *            源字符串
     * @return true|false是否数字的标志
     */
    public static boolean isNotNumeric(String str) {
        return isNumeric(str) ? false : true;
    }

    /**
     * 判断是浮点数，包括double和float
     *
     * @param str
     *            传入的字符串
     * @return true|false是浮点数返回true,否则返回false
     */
    public static boolean isDouble(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * 判断不是浮点数，包括double和float
     *
     * @param str
     *            传入的字符串
     * @return true|false是浮点数返回true,否则返回false
     */
    public static boolean isNotDouble(String str) {
        return isDouble(str) ? false : true;
    }

    /**
     * 判断是字母、数字、下划线组成
     *
     * @param src
     *            源字符串
     * @return true|false是字母、数字、下划线组成
     */
    public static boolean isLUD(String src) {
        boolean return_value = false;
        if (src != null && src.length() > 0) {
            Matcher m = Pattern.compile("^\\w+$").matcher(src);
            if (m.find()) {
                return_value = true;
            }
        }
        return return_value;
    }

    /**
     * 判断不是字母、数字、下划线组成
     *
     * @param src
     *            源字符串
     * @return true|false字母、数字、下划线组成
     */
    public static boolean isNotLUD(String src) {
        return isABC(src) ? false : true;
    }

    /**
     * 判断是纯字母组合
     *
     * @param src
     *            源字符串
     * @return true|false是否纯字母组合的标志
     */
    public static boolean isABC(String src) {
        boolean return_value = false;
        if (src != null && src.length() > 0) {
            Matcher m = Pattern.compile("^[a-z|A-Z]+$").matcher(src);
            if (m.find()) {
                return_value = true;
            }
        }
        return return_value;
    }

    /**
     * 判断不是纯字母组合
     *
     * @param src
     *            源字符串
     * @return true|false是否纯字母组合的标志
     */
    public static boolean isNotABC(String src) {
        return isABC(src) ? false : true;
    }

    /**
     * 判断是验证密码
     *
     * @param pwd
     *            密码字符串
     * @return true|false是否符合密码格式6-18位
     */
    public static boolean isPWD(String pwd) {
        Pattern patternPassword = Pattern.compile("^[A-Za-z0-9_]{6,18}$");
        Matcher matcher = patternPassword.matcher(pwd);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    /**
     * 判断不是验证密码
     *
     * @param pwd
     *            密码字符串
     * @return true|false是否符合密码格式6-18位
     */
    public static boolean isNotPWD(String pwd) {
        return isPWD(pwd) ? false : true;
    }

    /**
     * 判断验证IP是 v4地址
     *
     * @param ip
     *            IP地址
     * @return true|false
     */
    public static boolean isIpV4Address(String ip) {
        if (ValidatorUtil.isNotEmpty(ip)) {
            return false;
        }
        ip = ip.trim();
        Pattern pattern = Pattern.compile(
                "^(([0-9]|[1-9][0-9]|1[0-9]{2}|2([0-4][0-9]|5[0-5]))\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2([0-4][0-9]|5[0-5]))$");
        Matcher matcher = pattern.matcher(ip);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    /**
     * 判断验证IP不是 v4地址
     *
     * @param ip
     *            IP地址
     * @return true|false
     */
    public static boolean isNotIpV4Address(String ip) {
        return isIpV4Address(ip) ? false : true;
    }

    /**
     * 判断验证是身份证号
     *
     * @param idCard
     *            身份证字符串
     * @return true|false
     */
    public static boolean isIDCard(String idCard) {
        if (ValidatorUtil.isEmpty(idCard)) {
            return false;
        }
        idCard = idCard.trim();
        Pattern pattern = Pattern.compile("^(\\d{6})(\\d{4})(\\d{2})(\\d{2})(\\d{3})([0-9]|X|x)$");
        Matcher matcher = pattern.matcher(idCard);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    /**
     * 判断验证是不身份证号
     *
     * @param idCard
     *            身份证字符串
     * @return true|false
     */
    public static boolean isNotIDCard(String idCard) {
        return isIDCard(idCard) ? false : true;
    }

    /**
     * 判断验证是日期字符串
     *
     * @param date
     *            日期字符串
     * @return true|false
     */
    public static boolean isDate(String date) {
        if (ValidatorUtil.isNotEmpty(date)) {
            return false;
        }
        date = date.trim();
        Pattern pattern = Pattern.compile(
                "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
        Matcher matcher = pattern.matcher(date);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    /**
     * 判断验证不是日期字符串
     *
     * @param date
     *            日期字符串
     * @return true|false
     */
    public static boolean isNotDate(String date) {
        return isDate(date) ? false : true;
    }

    /**
     * 判断验证url地址
     *
     * @param url
     *            被验证的url
     * @return true|false
     */
    public static boolean isUrl(String url) {
        if (ValidatorUtil.isNotEmpty(url)) {
            return false;
        }
        url = url.trim();
        String regExp = "^((https|http|ftp|rtsp|mms)?://)"
                + "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?(([0-9]{1,3}\\.){3}[0-9]{1,3}"
                + "|([0-9a-z_!~*'()-]+\\.)*" + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\\." + "[a-z]{2,6})"
                + "(:[0-9]{1,5})?" + "((/?)|" + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(url);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    /**
     * 判断验证不是URL地址
     *
     * @param url
     *            被验证的url
     * @return true|false
     */
    public static boolean isNotUrl(String url) {
        return isUrl(url) ? false : true;
    }

    /**
     * 判断验证字符串是属于手机号码
     *
     * @param str
     *            需要检查的字符串
     * @return 是Mobile样式返回true,否则返回false
     */
    public static boolean isMobile(String str) {
        if (ValidatorUtil.isEmpty(str)) {
            return false;
        }
        str = str.trim();
        String regExp = "^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$";
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(str);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    /**
     * 判断验证字符串不是属于手机号码
     *
     * @param str
     *            需要检查的字符串
     * @return 不是Mobile样式返回true,否则返回false
     */
    public static boolean isNotMobile(String str) {
        return isMobile(str) ? false : true;
    }

    /**
     * 判断验证字符串是属于固定电话号码
     *
     * @param str
     *            需要检查的字符串
     * @return 是Phone样式返回true,否则返回false
     */
    public static boolean isPhone(String str) {
        if (ValidatorUtil.isEmpty(str)) {
            return false;
        }
        str = str.trim();
        String regExp = "^(0[0-9]{2,3}\\-)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?$";
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(str);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    /**
     * 判断验证字符串不是属于固定电话号码
     *
     * @param str
     *            需要检查的字符串
     * @return 不是Phone样式返回true,否则返回false
     */
    public static boolean isNotPhone(String str) {
        return isPhone(str) ? false : true;
    }

    /**
     * 判断验证字符串是属于邮政编码
     *
     * @param str
     *            需要检查的字符串
     * @return 是邮政编码样式返回true,否则返回false
     */
    public static boolean isPost(String str) {
        if (ValidatorUtil.isEmpty(str)) {
            return false;
        }
        str = str.trim();
        String regExp = "[1-9]{1}(\\d+){5}";
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(str);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    /**
     * 判断验证字符串不是属于邮政编码
     *
     * @param str
     *            需要检查的字符串
     * @return 不是邮政编码样式返回true,否则返回false
     */
    public static boolean isNotPost(String str) {
        return isPost(str) ? false : true;
    }

    /**
     * 判断输入的字符串是否符合Email样式
     *
     * @param str
     *            传入的字符串
     * @return 是Email样式返回true,否则返回false
     */
    public static boolean isEmail(String str) {
        Pattern pattern = Pattern.compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
        return pattern.matcher(str).matches();
    }

    /**
     * 判断输入的字符串不是否符合Email样式
     *
     * @param str
     *            传入的字符串
     * @return 不是Email样式返回true,否则返回false
     */
    public static boolean isNotEmail(String str) {
        return isEmail(str) ? false : true;
    }

    /**
     * 判断输入的字符串是纯汉字
     *
     * @param str
     *            传入的字符串
     * @return 如果是纯汉字返回true,否则返回false
     */
    public static boolean isChinese(String str) {
        Pattern pattern = Pattern.compile("[\u0391-\uFFE5]+$");
        return pattern.matcher(str).matches();
    }

    /**
     * 判断输入的字符串不是纯汉字
     *
     * @param str
     *            传入的字符串
     * @return 如果不是纯汉字返回true,否则返回false
     */
    public static boolean isNotChinese(String str) {
        return isChinese(str) ? false : true;
    }

    /**
     * 判断目标字符串是否大于指定的字符数（符合区间内的情况）
     *
     * @param str
     *            待查字符串
     * @param max
     *            最小字符数
     * @return true是|false否
     */
    public static boolean isMax(String str, int max) {
        int strLength = length(str);
        return StringUtils.isNotEmpty(str) && strLength > max ? true : false;
    }

    /**
     * 判断目标字符串是否不大于指定的字符数（符合区间内的情况）
     *
     * @param str
     *            待查字符串
     *            最小字符数
     * @return true是|false否
     */
    public static boolean isNotMax(String str, int max) {
        return !isMax(str, max);
    }

    /**
     * 判断目标字符串是否小于指定的字符数（符合区间内的情况）
     *
     * @param str
     *            待查字符串
     * @param min
     *            最小字符数
     * @return true是|false否
     */
    public static boolean isMin(String str, int min) {
        int strLength = length(str);
        return StringUtils.isNotEmpty(str) && strLength < min ? true : false;
    }

    /**
     * 判断目标字符串是否不小于指定的字符数（符合区间内的情况）
     *
     * @param str
     *            待查字符串
     * @param min
     *            最小字符数
     * @return true是|false否
     */
    public static boolean isNotMin(String str, int min) {
        return !isMin(str, min);
    }

    /**
     * 判断目标字符串是否在字符数最小值到最大值之间（符合区间内的情况）
     *
     * @param str
     *            待查字符串
     * @param min
     *            最小字符数
     * @param max
     *            最大字符数
     * @return true是|false否
     */
    public static boolean isMinToMax(String str, int min, int max) {
        int strLength = length(str);
        return StringUtils.isNotEmpty(str) && ((strLength >= min) && (strLength <= max)) ? true : false;
    }

    /**
     * 判断目标字符串是否在字符数最小值到最大值之间（不符合区间内的情况）
     *
     * @param str
     *            待查字符串
     * @param min
     *            最小字符数
     * @param max
     *            最大字符数
     * @return true是|false否
     */
    public static boolean isNotMinToMax(String str, int min, int max) {
        return !isMinToMax(str, min, max);
    }

    /**
     * 判断目标数字是否在指定的最小值到最大值之间（符合区间内的情况）
     *
     * @param val
     *            待查数字（整形）
     * @param min
     *            最小字符数
     * @param max
     *            最大字符数
     * @return true是|false否
     */
    public static boolean isMinToMax(int val, int min, int max) {
        return val >= min && val <= max ? true : false;
    }

    /**
     * 判断目标数字是否不在指定的最小值到最大值之间（不符合区间内的情况）
     *
     * @param val
     *            待查数字（整形）
     * @param min
     *            最小字符数
     * @param max
     *            最大字符数
     * @return true是|false否
     */
    public static boolean isNotMinToMax(int val, int min, int max) {
        return !isMinToMax(val, min, max);
    }

    /**
     * 得到一个字符串的长度,显示的长度。注：一个汉字或日韩文长度为2,英文字符长度为1
     *
     * param String
     *            str 需要得到长度的字符串
     * @return int 得到的字符串长度
     */
    public static int length(String str) {
        if (str == null)
            return 0;
        char[] c = str.toCharArray();
        int len = 0;
        for (int i = 0; i < c.length; i++) {
            len++;
            if (!isLetter(c[i])) {
                len++;
            }
        }
        return len;
    }

    /**
     * 判断是否为汉字或日韩（配合length方法使用）
     *
     * @param c
     *            字符
     * @return 是否是中文或日韩字符
     */
    public static boolean isLetter(char c) {
        int k = 0x80;
        return c / k == 0 ? true : false;
    }

    /**
     * 判断目标字符串是否符合指定的长度（符合长度的情况）。注：一个汉字或日韩文长度为2,英文字符长度为1
     *
     * @param str
     *            待查字符串
     * @param length
     *            长度
     * @return true是|false否
     */
    public static boolean isLength(String str, int length) {
        return StringUtils.isEmpty(str) || length(str) != length ? false : true;
    }

    /**
     * 判断目标字符串是否符合指定的长度（不符合长度的情况）。注：一个汉字或日韩文长度为2,英文字符长度为1
     *
     * @param str
     *            待查字符串
     * @param length
     *            长度
     * @return true是|false否
     */
    public static boolean isNotLength(String str, int length) {
        return StringUtils.isEmpty(str) || length(str) != length ? true : false;
    }

    /**
     * 判断目标字符串是32位UUID字符串（只能通过长度来判断是否是32位长度的字符串）
     *
     * @param str
     *            待查字符串
     * @return true是|false否
     */
    public static boolean isUUID(String str) {
        return isLength(str, 32);
    }

    /**
     * 判断目标字符串不是32位UUID字符串（只能通过长度来判断是否是32位长度的字符串）
     *
     * @param str
     *            待查字符串
     * @return true是|false否
     */
    public static boolean isNotUUID(String str) {
        return isNotLength(str, 32);
    }
}

