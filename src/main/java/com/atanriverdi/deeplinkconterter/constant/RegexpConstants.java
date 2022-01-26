package com.atanriverdi.deeplinkconterter.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class RegexpConstants {

    public static final String REGEX_WEB_URL = "(https://www.trendyol.com/)(([a-z/-]+(-p-)[1-9][0-9]*){1}[\\?=0-9A-Za-z&]*|(sr\\?q=[A-Za-z0-9%]+)|[A-Za-z\\/#0-9]+)";
    public static final String REGEX_DEEPLINK = "(ty://\\?Page=)(Product(&[A-Za-z=]+[1-9][0-9]+)*|Search(&Query=[A-Za-z0-9%]+)|[A-Za-z0-9]+)";

}
