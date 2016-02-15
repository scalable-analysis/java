package com.st.dmp.util;

import java.io.BufferedReader;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mongodb.DB;
import com.mongodb.Mongo;

public class CommUtil {
	public static List<String> person = new ArrayList<String>();
	public static void buildSortedLinkMap(Map<String, Integer> map, Map<String, Integer> newMap) {
		List<Map.Entry<String, Integer>> mappingList = null;

		mappingList = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
		Collections.sort(mappingList, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> mapping1, Map.Entry<String, Integer> mapping2) {
				return mapping2.getValue().compareTo(mapping1.getValue());
			}
		});
		for (Map.Entry<String, Integer> mapping : mappingList) {
			newMap.put(mapping.getKey(), mapping.getValue());
		}
	}
	
	public static void loadPerson(){
		System.out.println("开始加载人名!");
		
		person.add("﻿晏俊鹤");
		person.add("晏杰伦");
		person.add("晏文良");
		person.add("马达");
		person.add("赵晓雪");
		person.add("薛文泉");
		person.add("丁建伟");
		person.add("范小芬");
		person.add("文明");
		person.add("文鹏风");
		person.add("王丽");
		person.add("王建华");
		person.add("王梓任");
		person.add("王震");
		person.add("王宝珍");
		person.add("王敬亮");
		person.add("王丹");
		person.add("邓志勇");
		person.add("邓婕");
		person.add("尹会男");
		person.add("叶如红");
		person.add("付维娜");
		person.add("付双红");
		person.add("毕思倩");
		person.add("孙平");
		person.add("毛华强");
		person.add("孙一奇");
		person.add("孙媛媛");
		person.add("吴婷");
		person.add("杨倩莹");
		person.add("刘晓梅");
		person.add("刘俊明");
		person.add("刘海兵");
		person.add("刘卫华");
		person.add("刘奇龙");
		person.add("刘永辉");
		person.add("吕红");
		person.add("朱启新");
		person.add("张建辉");
		person.add("李向群");
		person.add("李子欣");
		person.add("李俊");
		person.add("李娟");
		person.add("李俊林");
		person.add("李桂福");
		person.add("陈其隆");
		person.add("陈明");
		person.add("邵彩云");
		person.add("杨志");
		person.add("杨波");
		person.add("杨凯吉");
		person.add("杨有成");
		person.add("杨巧敏");
		person.add("杨国栋");
		person.add("罗新磊");
		person.add("罗梅");
		person.add("罗永梅");
		person.add("罗欣悦");
		person.add("周婷");
		person.add("周玲玲");
		person.add("范文媛");
		person.add("胡水阳");
		person.add("胡君松");
		person.add("胡瑞");
		person.add("赵燕燕");
		person.add("赵静");
		person.add("赵红");
		person.add("袁滴");
		person.add("袁燕");
		person.add("郭颖欣");
		person.add("唐伟");
		person.add("贾天华");
		person.add("黄孝治");
		person.add("梁健");
		person.add("彭虎");
		person.add("盛伟刚");
		person.add("谢敏");
		person.add("曾水秀");
		person.add("曾维干");
		person.add("王菊");
		person.add("曾晖");
		person.add("曾莹");
		person.add("蔡准");
		person.add("谭红");
		person.add("蔡滔");
		person.add("黎婷婷");
		person.add("燕良华");
		person.add("戴剑敏");
		person.add("谭位旭");
		person.add("佘季");
		person.add("刘婷");
		person.add("李莎");
		person.add("夏婷");
		person.add("王玲");
		person.add("谭黎");
		person.add("陈晟");
		person.add("肖杨");
		person.add("邓健");
		person.add("龙平");
		person.add("叶波");
		person.add("阳勇");
		person.add("刘超");
		person.add("张玲");
		person.add("张琴丽");
		person.add("李远焕");
		person.add("李勇");
		person.add("陈丽云");
		person.add("肖琼洁");
		person.add("吴卫国");
		person.add("杨欢");
		person.add("郑志强");
		person.add("贺小红");
		person.add("杨淅");
		person.add("李珍珍");
		person.add("李珍宝");
		person.add("李珍婆");
		person.add("尹敏");
		person.add("马飞");
		person.add("尹文");
		person.add("杨隆");
		person.add("杨金");
		person.add("丁锦");
		person.add("丁开水");
		person.add("黎春慧");
		person.add("春慧");
		person.add("吴丹惠");
		person.add("张苑博");
		person.add("李杰邦");
		person.add("王文材");
		person.add("赵文渊");
		person.add("孙文达");
		person.add("钱明轩");
		person.add("刘彬华");
		person.add("汤瀚福");
		person.add("厉轩");
		person.add("周杰裕");
		person.add("谭俊胜");
		person.add("肖睿轩");
		person.add("龙远旭");
		person.add("邓寒烟");
		person.add("张杰毅");
		person.add("李鸿灿");
		person.add("王远鸿");
		person.add("赵文兴");
		person.add("孙志国");
		person.add("钱鼎遥");
		person.add("刘镜宇");
		person.add("汤鸿承");
		person.add("周俊伟");
		person.add("戴鼎翎");
		person.add("谭修永");
		person.add("肖世图");
		person.add("龙俊新");
		person.add("邓冠宇");
		person.add("张寒云");
		person.add("李瀚君");
		person.add("王弘玮");
		person.add("赵韵杰");
		person.add("孙杰雄");
		person.add("钱文霖");
		person.add("刘俊帆");
		person.add("汤昌凯");
		person.add("周德春");
		person.add("戴杰书");
		person.add("谭昌翔");
		person.add("肖德元");
		person.add("龙乐奇");
		person.add("邓旭翰");
		person.add("张君乾");
		person.add("李任平");
		person.add("王泽信");
		person.add("赵圣国");
		person.add("孙弘耀");
		person.add("钱博远");
		person.add("刘月阳");
		person.add("汤泰玄");
		person.add("周伦博");
		person.add("戴龙德");
		person.add("谭鼎佑");
		person.add("肖哲瀚");
		person.add("龙弘振");
		person.add("邓昌云");
		person.add("张新远");
		person.add("李贵依");
		person.add("王俊颜");
		person.add("赵文来");
		person.add("孙玮轩");
		person.add("钱鼎鸿");
		person.add("刘秋风");
		person.add("汤荣银");
		person.add("周志辉");
		person.add("戴博伦");
		person.add("谭荣春");
		person.add("肖睿博");
		person.add("龙世康");
		person.add("邓瀚景");
		person.add("张智涛");
		person.add("李晋运");
		person.add("王杰翔");
		person.add("赵晋刚");
		person.add("孙明新");
		person.add("钱展瀚");
		person.add("刘杰依");
		person.add("汤昌毅");
		person.add("周风涛");
		person.add("戴寒承");
		person.add("谭圣帆");
		person.add("肖明志");
		person.add("龙旭冠");
		person.add("邓玮博");
		person.add("张宇灿");
		person.add("李雨瑞");
		person.add("王清泉");
		person.add("赵月星");
		person.add("孙志欧");
		person.add("钱德宝");
		person.add("刘文韵");
		person.add("汤文祖");
		person.add("周文春");
		person.add("戴韵宇");
		person.add("谭光耀");
		person.add("肖弘靖");
		person.add("龙君翔");
		person.add("邓棋博");
		person.add("张弘浩");
		person.add("李宇驰");
		person.add("王文驰");
		person.add("赵彬逸");
		person.add("孙瀚舟");
		person.add("钱新杰");
		person.add("刘哲南");
		person.add("汤志邦");
		person.add("周泽君");
		person.add("戴文辰");
		person.add("龙玮畅");
		person.add("龙彬城");
		person.add("王涛胜");
		person.add("王晋永");
		person.add("周涛羽");
		person.add("周鼎宇");
		person.add("余棋翰");
		person.add("于贵博");
		person.add("肖桦楠");
		person.add("佘志伟");
		person.add("麦弘邦");
		person.add("明瑞");
		person.add("邹柯勤");
		person.add("周青松");
		person.add("汪德轩");
		person.add("云翰辰");
		person.add("远乔");
		person.add("国鸿祥");
		person.add("余翰颜");
		person.add("杉晖");
		person.add("于金福");
		person.add("于远瑾");
		person.add("博尘");
		person.add("晋岳");
		person.add("云德石");
		person.add("海杉");
		person.add("于鼎承");
		person.add("明健");
		person.add("明瀚诚");
		person.add("云浩新");
		person.add("彬琪");
		person.add("城德");
		person.add("余鼎智");
		person.add("余圣天");
		person.add("于晋枝");
		person.add("于德君");
		person.add("云曲漾");
		person.add("明翰逸");
		person.add("明若菲");
		person.add("于博泰");
		person.add("于秀永");
		person.add("明宇瀚");
		person.add("君裕");
		person.add("彬仁顺");
		person.add("鼎风");
		person.add("彬辰逸");
		person.add("余杰奥");
		person.add("于百川");
		person.add("于瀚德");
		person.add("于浩诚");
		person.add("明杰宏");
		person.add("明昌奇");
		person.add("明丰瞻");
		person.add("麦博仁");
		person.add("余皓");
		person.add("文俊龙");
		person.add("云彬锐");
		person.add("云畅瑞");
		person.add("彬昌贤");
		person.add("雪松");
		person.add("余玉燕");
		person.add("麦春柏");
		person.add("于楠素");
		person.add("麦君壁");
		person.add("明睿娇");
		person.add("周甜英");
		person.add("余向卉");
		person.add("明丽梅");
		person.add("芙香");
		person.add("文云昕");
		person.add("馥烟");
		person.add("云娜盈");
		person.add("娜漫");
		person.add("王凝雁");
		person.add("余紫槐");
		person.add("麦雪娅");
		person.add("麦冰兰");
		person.add("于幻莲");
		person.add("明雪柳");
		person.add("麦冰旋");
		person.add("于甜熙");
		person.add("文沛珊");
		person.add("明语雅");
		person.add("明芳虹");
		person.add("文若雪");
		person.add("乐雨珍");
		person.add("春竹");
		person.add("雨妙香");
		person.add("余花萍");
		person.add("云惜茵");
		person.add("周沐娟");
		person.add("月爽");
		person.add("于瑜柔");
		person.add("红芍");
		person.add("于秋柔");
		person.add("文紫呜");
		person.add("明娇语");
		person.add("明雅瑾");
		person.add("秋荷");
		person.add("茹婕");
		person.add("余冰婉");
		person.add("周雨双");
		person.add("周雨雪");
		person.add("明映倚");
		person.add("于翠竹");
		person.add("珍美");
		person.add("明怡钰");
		person.add("文馨梨");
		person.add("乐娅楠");
		person.add("晓巧");
		person.add("雪芳");
		person.add("雨韵雪");
		person.add("雨以菱");
		person.add("静影");
		person.add("明妙筠");
		person.add("明琪涵");
		person.add("余依云");
		person.add("汪凌瑶");
		person.add("汪巧春");
		person.add("安荷");
		person.add("汪佳红");
		person.add("周雁卉");
		person.add("菲霞");
		person.add("莉梦");
		person.add("周梦芹");
		person.add("于月虹");
		person.add("文以萱");
		person.add("周红芳");
		person.add("明水云");
		person.add("乐幻丝");
		person.add("余语凤");
		person.add("香怡");
		person.add("汪婷莉");
		person.add("文依柏");
		person.add("汪润慧");
		person.add("周雪曦");
		person.add("璇盈");
		person.add("文平绿");
		person.add("乐丹");
		person.add("雨紫真");
		person.add("余念薇");
		person.add("文春儿");
		person.add("谷波");
		person.add("菡颖");
		person.add("余秋春");
		person.add("余蕾碧");
		person.add("于珊琴");
		person.add("翠柳");
		person.add("汪芹香");
		person.add("周云燕");
		person.add("周紫超");
		person.add("文艳姗");
		person.add("于瑜昕");
		person.add("乐念芹");
		person.add("余静琪");
		person.add("柔雅");
		person.add("余宛凝");
		person.add("沛菡");
		person.add("文醉蝶");
		person.add("汪语嫣");
		person.add("周幻紫");
		person.add("萱雅");
		person.add("周绮彤");
		person.add("汪香菱");
		person.add("若蕾");
		person.add("周晓玫");
		person.add("余馨蕊");
		person.add("于水瑶");
		person.add("余珊缦");
		person.add("雨芳菲");
		person.add("余海桃");
		person.add("雅儿");
		person.add("蕾雅");
		person.add("文碧彤");
		person.add("黛亦");
		person.add("余蝶葵");
		person.add("淑颖");
		person.add("文寻春");
		person.add("汪梅雪");
		person.add("余如春");
		person.add("于紫怡");
		person.add("汪妍芳");
		person.add("余静璇");
		person.add("乐昕怡");
		person.add("余醉珊");
		person.add("紫芳");
		person.add("文雨嘉");
		person.add("汪晓青");
		person.add("文艳蓉");
		person.add("采菡");
		person.add("文清云");
		person.add("文婕莲");
		person.add("于冰烟");
		person.add("余玫雅");
		person.add("麦雅彤");
		person.add("麦茹桃");
		person.add("麦紫薇");
		person.add("麦思芳");
		person.add("余冰颖");
		person.add("余晓亦");
		person.add("雨雯");
		person.add("雨欣");
		person.add("晓黛");
		person.add("麦霄若");
		person.add("余文霞");
		person.add("曦冰");
		person.add("静璇");
		person.add("余菲香");
		person.add("妙昕");
		person.add("汪菲尔");
		person.add("王映秋");
		person.add("麦雪旋");
		person.add("周静香");
		person.add("周凤媛");
		person.add("王映倚");
		person.add("王菡幻");
		person.add("宛儿");
		person.add("汪盼夏");
		person.add("菲瑶");
		person.add("紫蓝");
		person.add("余芳蕊");
		person.add("周虞芝");
		person.add("周凌春");
		person.add("秋月");
		person.add("麦玉娟");
		person.add("雪萍");
		person.add("王晓红");
		person.add("孙妙海");
		person.add("刘寻芹");
		person.add("王寒菊");
		person.add("孙竹青");
		person.add("刘晓韵");
		person.add("紫月");
		person.add("于昕香");
		person.add("柳旋");
		person.add("虞凤");
		person.add("于妙云");
		person.add("余睿茹");
		person.add("水莹");
		person.add("麦冬儿");
		person.add("王芷蕊");
		person.add("王楠素");
		person.add("梦槐");
		person.add("芳枫");
		person.add("孙旋蓉");
		person.add("雨兰");
		person.add("佩虹");
		person.add("凤筠");
		person.add("南媛");
		person.add("孙艳琴");
		person.add("王新华");
		person.add("麦翠柔");
		person.add("海冬");
		person.add("余幻莲");
		person.add("麦茹雪");
		person.add("芳辰");
		person.add("琼芝");
		person.add("月霞");
		person.add("宛梦");
		person.add("麦梦寒");
		person.add("玉芷");
		person.add("林曼舞");
		person.add("林蕊馨");
		person.add("林珊缦");
		person.add("雨花");
		person.add("灵薇");
		person.add("诗露岚");
		person.add("雨莉");
		person.add("于婉君");
		person.add("于清云");
		person.add("于如敏");
		person.add("麦秋柏");
		person.add("可紫芹");
		person.add("于琪涵");
		person.add("麦月婵");
		person.add("曼倚");
		person.add("夏菡");
		person.add("馥烟");
		person.add("妍舒");
		person.add("芷蕾");
		person.add("雪枫");
		person.add("诗佳婉");
		person.add("缘媛");
		person.add("可昕");
		person.add("曼菲楠");
		person.add("麦馨彤");
		person.add("于青旋");
		person.add("燕芊");
		person.add("诗素月");
		person.add("曼虹媛");
		person.add("雅芙");
		person.add("丹雪");
		person.add("语蝶");
		person.add("若倩");
		person.add("佳怡");
		person.add("云青");
		person.add("寒艳");
		person.add("诗芹桑");
		person.add("曼媛嫣");
		person.add("雅阳");
		person.add("曼紫菱");
		person.add("曼芊黛");
		person.add("诗凌晴");
		person.add("可菲玉");
		person.add("绮梅");
		person.add("迎夏");
		person.add("诗晓华");
		person.add("冬娜");
		person.add("嫣丽");
		person.add("怡冰");
		person.add("佳思");
		person.add("君洁");
		person.add("晏旭磊");
		person.add("晏贤德");
		person.add("晏博鹤");
		person.add("晏昌雷");
		person.add("晏晋若");
		person.add("晏宇哲");
		person.add("晏展豪");
		person.add("晏寒哲");
		person.add("晏野棋");
		person.add("晏棋信");
		person.add("晏风振");
		person.add("晏辉睿");
		person.add("晏博帆");
		person.add("晏喻义");
		person.add("晏世奇");
		person.add("晏致浩");
		person.add("晏仁承");
		person.add("晏辉桦");
		person.add("晏德兴");
		person.add("晏昌舟");
		person.add("晏玮业");
		person.add("晏寒远");
		person.add("晏杉国");
		person.add("晏弘遥");
		person.add("晏星韵");
		person.add("晏博志");
		person.add("晏文帆");
		person.add("晏旭翔");
		person.add("晏鼎遥");
		person.add("晏君旭");
		person.add("晏博江");
		person.add("晏智深");
		person.add("晏游浩");
		person.add("晏雨畅");
		person.add("晏寒烟");
		person.add("晏泽天");
		person.add("晏游羽");
		person.add("晏翰德");
		person.add("晏文栋");
		person.add("晏智尧");
		person.add("晏煜祺");
		person.add("晏世承");
		person.add("晏文尧");
		person.add("晏彬浪");
		person.add("晏展康");
		person.add("晏泰舟");
		person.add("晏浩图");
		person.add("晏桦晨");
		person.add("晏善仁");
		person.add("晏韵鸿");
		person.add("晏龙");
		person.add("晏德君");
		person.add("晏世图");
		person.add("晏依伦");
		person.add("晏弘言");
		person.add("晏晋文");
		person.add("晏杰书");
		person.add("晏翰韵");
		person.add("晏天佑");
		person.add("晏旭秉");
		person.add("晏博哲");
		person.add("晏星尚");
		person.add("晏劲宇");
		person.add("晏悟浩");
		person.add("晏依昭");
		person.add("晏德石");
		person.add("晏翰瑞");
		person.add("晏俊轩");
		person.add("晏博岩");
		person.add("晏风琪");
		person.add("晏博靖");
		person.add("晏晋博");
		person.add("晏弘涛");
		person.add("晏松德");
		person.add("晏睿思");
		person.add("晏文恩");
		person.add("晏旭棋");
		person.add("晏志邦");
		person.add("晏圣涛");
		person.add("晏弘国");
		person.add("晏万松");
		person.add("晏际云");
		person.add("晏棋舟");
		person.add("晏雨鸿");
		person.add("晏健江");
		person.add("晏远哲");
		person.add("晏博遥");
		person.add("晏鼎风");
		person.add("晏圣琪");
		person.add("晏宇辉");
		person.add("晏鼎震");
		person.add("晏雨泽");
		person.add("晏彬泰");
		person.add("晏哲楠");
		person.add("晏智桦");
		person.add("晏远瀚");
		person.add("晏彬楠");
		person.add("晏泽信");
		person.add("晏丰瞻");
		person.add("晏鼎维");
		person.add("晏棋鸿");
		person.add("晏尚兴");
		person.add("欧轩材");
		person.add("欧星玉");
		person.add("欧翱翔");
		person.add("欧鸿云");
		person.add("欧旭尧");
		person.add("欧雨国");
		person.add("欧远兴");
		person.add("欧德远");
		person.add("欧哲伦");
		person.add("欧辉帆");
		person.add("欧江雁");
		person.add("欧寒江");
		person.add("欧鸿叶");
		person.add("欧绍辉");
		person.add("欧烨伟");
		person.add("欧瀚君");
		person.add("欧风海");
		person.add("欧秀琪");
		person.add("欧喻义");
		person.add("欧丰瞻");
		person.add("欧涛图");
		person.add("欧鸿杰");
		person.add("欧弘毅");
		person.add("欧玮材");
		person.add("欧远欧");
		person.add("欧雨桦");
		person.add("欧楠佑");
		person.add("欧冬雪");
		person.add("欧翰颜");
		person.add("欧苑涛");
		person.add("欧远辰");
		person.add("欧松德");
		person.add("欧桦影");
		person.add("欧彬逸");
		person.add("欧松德");
		person.add("欧桦盛");
		person.add("欧宇琪");
		person.add("欧俊文");
		person.add("欧君辰");
		person.add("欧君伟");
		person.add("欧博盛");
		person.add("欧宇畅");
		person.add("欧修远");
		person.add("欧新杰");
		person.add("欧智畅");
		person.add("欧圣伯");
		person.add("欧文嘉");
		person.add("欧恩益");
		person.add("欧依祥");
		person.add("欧鸿震");
		person.add("欧泰润");
		person.add("欧风宇");
		person.add("欧博睿");
		person.add("欧鼎棋");
		person.add("欧哲瑾");
		person.add("欧翰运");
		person.add("欧桦润");
		person.add("欧鼎宇");
		person.add("欧鹏涛");
		person.add("欧智文");
		person.add("欧越泽");
		person.add("欧晋尚");
		person.add("欧桦瑞");
		person.add("欧韶华");
		person.add("欧星承");
		person.add("欧浩南");
		person.add("欧弘渊");
		person.add("欧杰宇");
		person.add("欧文彤");
		person.add("欧文炫");
		person.add("欧元奇");
		person.add("欧紫敬");
		person.add("欧涛天");
		person.add("欧昊柏");
		person.add("欧旭云");
		person.add("欧翰星");
		person.add("欧羽德");
		person.add("欧远皓");
		person.add("欧嘉懿");
		person.add("欧雨瑞");
		person.add("欧旭帆");
		person.add("欧彬耀");
		person.add("欧晋阳");
		person.add("欧鼎拓");
		person.add("欧玮天");
		person.add("欧泽石");
		person.add("欧翰阳");
		person.add("欧文庭");
		person.add("欧昌星");
		person.add("欧圆融");
		person.add("欧凯欧");
		person.add("欧健鸿");
		person.add("欧泰泽");
		person.add("欧翰思");
		person.add("欧君谦");
		person.add("欧健江");
		person.add("欧晋唯");
		person.add("欧桦德");
		person.add("欧博君");
		person.add("峻熙");
		person.add("欧晋翰");
		person.add("欧弘雨");
		person.add("欧旭畅");
		person.add("欧翰锦");
		person.add("欧晋诚");
		person.add("欧博轩");
		person.add("欧圣宇");
		person.add("欧材旭");
		person.add("欧文崇");
		person.add("欧玮耀");
		person.add("欧晋华");
		person.add("欧鼎豪");
		person.add("欧彬锐");
		person.add("欧涛柏");
		person.add("欧德云");
		person.add("欧雨博");
		person.add("欧彤辰");
		person.add("欧文城");
		person.add("欧文胜");
		person.add("欧荣畅");
		person.add("楚弘尘");
		person.add("楚泽元");
		person.add("楚瀚华");
		person.add("楚杰锐");
		person.add("楚寒沙");
		person.add("楚彬琦");
		person.add("楚承天");
		person.add("楚韵轩");
		person.add("楚俊云");
		person.add("楚浩轩");
		person.add("楚安皓");
		person.add("楚知智");
		person.add("楚宇恒");
		person.add("楚瑾瑜");
		person.add("楚安福");
		person.add("楚远松");
		person.add("楚昌辉");
		person.add("楚博雪");
		person.add("楚苑棋");
		person.add("楚泰轩");
		person.add("楚玮轩");
		person.add("楚玮誉");
		person.add("楚材宇");
		person.add("楚哲楠");
		person.add("楚文健");
		person.add("楚满杉");
		person.add("楚弘易");
		person.add("楚瀚阳");
		person.add("楚鸿鸽");
		person.add("楚浩玄");
		person.add("楚瀚德");
		person.add("楚君图");
		person.add("楚博哲");
		person.add("楚博光");
		person.add("楚旭民");
		person.add("楚文强");
		person.add("楚弘翎");
		person.add("楚宇欢");
		person.add("楚圆融");
		person.add("楚寒彤");
		person.add("楚天磊");
		person.add("楚弘恒");
		person.add("楚涛博");
		person.add("楚玮栋");
		person.add("楚彬庭");
		person.add("楚依振");
		person.add("楚志涛");
		person.add("楚畅松");
		person.add("楚棋博");
		person.add("楚旭辉");
		person.add("楚阳诚");
		person.add("楚彤斌");
		person.add("楚文志");
		person.add("楚玮润");
		person.add("楚杰东");
		person.add("楚博嘉");
		person.add("楚依夫");
		person.add("楚博睿");
		person.add("楚冬铭");
		person.add("楚布苛");
		person.add("楚文进");
		person.add("晏泰玄");
		person.add("晏弘恒");
		person.add("晏弘乾");
		person.add("晏楠熙");
		person.add("晏彬逸");
		person.add("晏恒城");
		person.add("晏泽润");
		person.add("晏昌如");
		person.add("杨朝来");
		person.add("蒋平");
		person.add("汤灿华");
		person.add("晏文凯");
		person.add("晏玮云");
		person.add("晏晋德");
		person.add("晏凯风");
		person.add("晏玮楷");
		person.add("晏依庆");
		person.add("晏文拓");
		person.add("楚鸿杰");
		person.add("楚涛国");
		person.add("楚弘豪");
		person.add("楚棋依");
		person.add("楚俊明");
		person.add("楚晋龙");
		person.add("楚玮睿");
		person.add("楚玮康");
		person.add("楚圣华");
		person.add("楚顺庆");
		person.add("楚圣泽");
		person.add("楚彬嘉");
		person.add("楚昌雷");
		person.add("楚泽仁");
		person.add("楚圣磊");
		person.add("楚星野");
		person.add("楚圣天");
		person.add("楚文鼎");
		person.add("楚明宇");
		person.add("楚羽德");
		person.add("楚彬鸿");
		person.add("楚宇棋");
		person.add("楚宇朋");
		person.add("楚强傲");
		person.add("楚博顺");
		person.add("楚文材");
		person.add("楚观云");
		person.add("楚棋圣");
		person.add("楚新知");
		person.add("楚俊皓");
		person.add("楚千枝");
		person.add("楚文材");
		person.add("楚靖琪");
		person.add("楚菲文");
		person.add("楚元奇");
		person.add("楚德泽");
		person.add("楚君达");
		person.add("楚恒城");
		person.add("楚紫明");
		person.add("楚君拓");
		person.add("楚翰玮");
		person.add("楚弘润");
		person.add("楚岸青");
		person.add("楚智恒");
		person.add("楚晋国");
		person.add("楚雷豫");
		person.add("楚文涛");
		person.add("楚荣杭");
		person.add("楚杰宏");
		person.add("楚翰运");
		person.add("楚泽文");
		person.add("楚博振");
		person.add("楚高远");
		person.add("楚楷瑞");
		person.add("楚晋城");
		person.add("楚鸿锦");
		person.add("楚弘文");
		person.add("楚杰毅");
		person.add("楚弘信");
		person.add("刘含樱");
		person.add("菲槐");
		person.add("万水莹");
		person.add("孙水冰");
		person.add("李彤旋");
		person.add("周月霞");
		person.add("丹华");
		person.add("海亦");
		person.add("周晴柔");
		person.add("刘曦奇");
		person.add("刘歆翠");
		person.add("刘曦冰");
		person.add("灵薇");
		person.add("如凤");
		person.add("习秀宇");
		person.add("习晓然");
		person.add("习鸿涛");
		person.add("习文泽");
		person.add("习新知");
		person.add("习浩彦");
		person.add("习展博");
		person.add("习城城");
		person.add("习远信");
		person.add("习胜天");
		person.add("习翰瑞");
		person.add("习昌辰");
		person.add("习羽海");
		person.add("习德江");
		person.add("习博弘");
		person.add("习泽伟");
		person.add("习博轩");
		person.add("习宇轩");
		person.add("习博庭");
		person.add("习旭图");
		person.add("习文勇");
		person.add("习俊磊");
		person.add("习唯仁");
		person.add("习星欧");
		person.add("习彬瑞");
		person.add("习涛旷");
		person.add("习云轻");
		person.add("习博胜");
		person.add("习君乾");
		person.add("习明辉");
		person.add("习杰雷");
		person.add("习依隆");
		person.add("习棋胜");
		person.add("习游振");
		person.add("习昌尘");
		person.add("习弘明");
		person.add("习鼎翎");
		person.add("习玮柏");
		person.add("习泰康");
		person.add("习弘岩");
		person.add("习文兴");
		person.add("习文翎");
		person.add("习文盛");
		person.add("习振豪");
		person.add("习文明");
		person.add("习伯舟");
		person.add("习彬泽");
		person.add("习翰文");
		person.add("习世高");
		person.add("习哲诚");
		person.add("习荣基");
		person.add("习弘玄");
		person.add("习旭熙");
		person.add("习杰东");
		person.add("习俊玮");
		person.add("习志新");
		person.add("习旭明");
		person.add("习圣杰");
		person.add("习智翰");
		person.add("习博伟");
		person.add("习楷明");
		person.add("习秀辰");
		person.add("习弘文");
		person.add("习俊诚");
		person.add("习浩翔");
		person.add("习旭辰");
		person.add("习闻枝");
		person.add("习君瑞");
		person.add("习博锦");
		person.add("习宇杰");
		person.add("习智旭");
		person.add("习昌毅");
		person.add("习远若");
		person.add("习世帆");
		person.add("习俊康");
		person.add("习云流");
		person.add("习展新");
		person.add("习昌瀚");
		person.add("习鼎辉");
		person.add("习文伯");
		person.add("习天德");
		person.add("习韵鸿");
		person.add("习松啸");
		person.add("习智永");
		person.add("习俊星");
		person.add("习明琪");
		person.add("习德毅");
		person.add("习文良");
		person.add("习涛翔");
		person.add("习浩玄");
		person.add("习文海");
		person.add("习哲龙");
		person.add("习泽龙");
		person.add("习彬韵");
		person.add("习浩欧");
		person.add("习远欧");
		person.add("习旭畅");
		person.add("习柯远");
		person.add("习文哲");
		person.add("习尧嘉");
		person.add("习智翎");
		person.add("习玮琦");
		person.add("习杰依");
		person.add("习畅风");
		person.add("习鸿萧");
		person.add("习玮风");
		person.add("习泽鸿");
		person.add("习致鸣");

		System.out.println("人名加载完毕！");
	}
	public static DB getConnection(String url, int port , String dbName){
		Mongo mongo = null;
		try {
			mongo = new Mongo(url,port);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		DB db = mongo.getDB(dbName);
		return db;		
	}
	
	public static void main(String[] args){
		System.out.println(person.size());
		loadPerson();
		for(int i = 0 ; i < 10 ; i++){
			int no = (int)(Math.random()*1023);
			System.out.println(person.get(no));
		}
		System.out.println(person.size());
	}
}
