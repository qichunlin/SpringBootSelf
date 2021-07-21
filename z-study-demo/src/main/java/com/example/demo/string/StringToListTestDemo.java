package com.example.demo.string;

import java.util.Arrays;
import java.util.List;

/**
 * 字符串转List集合
 *
 * @author legend
 * @version 1.0
 * @description
 * @date 2021/7/15
 */
public class StringToListTestDemo {

    public static void main(String[] args) {
        String s = "11897, 12046, 20087, 20088, 20089, 20090, 20091, 20092, 20093, 20094, 20095, 20096, 20097, 20098, 20099, 20100, 20101, 20102, 20103, 20104, 20105, 20106, 20107, 20108, 20109, 20110, 20111, 20112, 20113, 20114, 20115, 20116, 20117, 20118, 20119, 20120, 20121, 20122, 20123, 20124, 20125, 20126, 20127, 20128, 20129, 20130, 20131, 20132, 20133, 20134, 20135, 20136, 20137, 20138, 20139, 20140, 20141, 20142, 20143, 20144, 20145, 20146, 20147, 20148, 20149, 20150, 20151, 20152, 20153, 20154, 20155, 20156, 20157, 20158, 20159, 20160, 20161, 20162, 20163, 20164, 20165, 20166, 20167, 20168, 20169, 20170, 20171, 20172, 20173, 20174, 20175, 20176, 20177, 20178, 20179, 20180, 20181, 20182, 20183, 20184, 20185, 20186, 20187, 20188, 20189, 20190, 20191, 20192, 20193, 20194, 20195, 20196, 20197, 20198, 20199, 20200, 20201, 20202, 20203, 20204, 20205, 20206, 20207, 20208, 20209, 20210, 20211, 20212, 20213, 20214, 20215, 20216, 20217, 20218, 20219, 20220, 20223, 20226, 20229, 20233, 20235, 20237, 20239, 20241, 20242, 20243, 20244, 20245, 20246, 20247, 20248, 20250, 20252, 20253, 20254, 20255, 20257, 20259, 20262, 20264, 20265, 20267, 20269, 20270, 20272, 20273, 20275, 20276, 20278, 20279, 20281, 20282, 20284, 20285, 20287, 20288, 20290, 20291, 20292, 20293, 20294, 20295, 20296, 20297, 20298, 20299, 20300, 20301, 20302, 20303, 20304, 20305, 20307, 20308, 20310, 20312, 20313, 20314, 20315, 20316, 20317, 20319, 20320, 20322, 20323, 20326, 20327, 20329, 20332, 20334, 20336, 20337, 20339, 20341, 20342, 20343, 20345, 20346, 20348, 20349, 24681, 25743, 26082, 26083, 26084, 26168, 26343, 26344, 26345, 26346, 26347, 26348, 26349, 26350, 26351, 26352, 26353, 26354, 26355, 26356, 26357, 26358, 26359, 26360, 26361, 26362, 26363, 26364, 26365, 26366, 26367, 26368, 26369, 26370, 26371, 26372, 26373, 26374, 26375, 26376, 26377, 26378, 26379, 26380, 26381, 26382, 26383, 26384, 26385, 26386, 26387, 26388, 26389, 26390, 26391, 26392, 26393, 26394, 26395, 26396, 27088, 27702, 27703, 27704, 27705, 27706, 29745, 29746, 29747, 29748, 29749, 30114, 30115, 30206, 30364, 30778, 30779, 30780, 30781, 30810, 30811, 30812, 30818, 32461, 32588, 32589, 32590, 32603, 32679, 32680, 32729, 32730, 32731, 32732, 32843, 32844, 33292, 33307, 33308, 33309, 33310, 33370, 33884, 33890, 33892, 33893, 34345, 34986, 34987, 35312, 35600, 35601, 35602, 35603, 35970, 35971, 35972, 35973, 36088, 36277, 36278, 36279, 36711, 36712, 36717, 36718";
        String s2 = "12140, 12141, 12142, 12143, 10564, 12144, 12145, 12146, 12147, 12148, 12149, 12150, 12151, 12152, 10565, 12153, 12154, 12155, 12157, 12158, 12159, 12160, 12161, 10566, 12162, 12163, 12164, 12165, 12166, 12167, 12168, 12169, 12170, 10567, 12171, 12172, 12173, 12174, 12175, 12176, 12177, 12178, 12179, 10568, 12180, 12181, 12182, 12183, 12184, 12185, 12186, 12187, 12188, 10569, 12189, 12190, 12191, 12192, 12193, 12194, 12195, 12196, 12197, 10570, 12198, 12199, 12200, 12201, 12202, 12203, 12204, 12205, 12206, 10571, 12207, 12208, 12209, 12210, 12211, 12212, 12213, 12214, 12215, 10572, 12216, 12217, 12218, 12219, 12220, 12221, 12222, 12223, 12224, 10573, 12225, 12226, 12227, 12228, 12229, 12230, 12231, 12232, 12233, 10574, 12234, 12235, 12236, 12237, 12238, 12239, 12240, 12241, 12242, 10575, 12243, 12244, 12245, 12246, 12247, 12248, 12249, 12250, 12251, 10576, 12252, 12253, 12254, 12255, 12256, 12257, 12258, 12259, 12260, 10577, 12261, 12262, 12263, 12264, 12265, 12266, 12267, 12268, 12269, 10578, 12270, 12271, 12272, 12273, 12274, 12276, 12277, 10579, 12279, 12282, 12285, 12286, 12287, 10580, 12288, 12289, 12290, 12292, 12293, 12294, 12295, 12296, 10581, 12297, 12298, 12302, 12305, 10582, 12307, 12308, 12309, 12310, 12311, 12312, 12313, 12314, 10583, 12315, 12319, 12326, 12328, 12330, 12332, 12333, 12335, 12337, 12339, 12341, 12346, 18090, 18101, 18102, 18103, 18118, 18119, 18120, 20800, 20801, 20802, 20803, 20804, 20805, 20806, 20807, 20808, 20809, 20810, 20811, 20812, 20813, 20814, 20815, 20816, 20817, 20818, 20819, 20820, 20821, 20822, 20823, 20824, 20825, 20826, 20827, 20828, 20829, 20830, 20831, 20832, 20833, 20834, 20835, 20836, 20837, 20838, 20839, 20840, 20841, 20842, 20843, 20844, 20845, 20846, 20847, 20848, 20849, 22847, 22848, 22849, 22850, 22851, 22852, 22853, 22854, 22855, 22856, 22857, 22858, 22859, 24658, 24659, 24660, 24661, 24662, 24663, 24664, 24665, 24666, 24667, 24668, 24669, 24670, 24671, 24672, 24673, 24674, 24675, 24676, 24677, 24678, 24679, 24680, 26169, 26542, 26543, 26544, 26545, 26546, 26547, 26548, 26549, 26550, 27177, 27201, 27221, 28622, 28623, 28624, 28625, 28626, 28627, 28628, 28629, 28630, 28631, 28632, 28633, 28634, 28635, 28636, 28637, 28638, 28639, 28640, 28641, 28642, 28643, 28644, 28645, 28646, 28647, 28648, 28649, 28650, 28651, 28652, 32471, 32472, 32473, 32474, 32475, 32476, 32477, 32478, 32479, 32480, 32481, 32482, 32483, 32484, 32485, 32486, 32487, 32488, 32489, 32490, 32491, 32492, 32493, 32494, 32495, 32496, 32497, 32498, 32499, 32500, 32501, 32502, 32503, 32504, 32505, 32827, 32828, 32829, 32830, 32831, 32832, 32833, 32834, 32835, 32836, 32837, 32838, 32839, 32840, 32841, 32842, 33365, 33448, 33551, 34398, 35573, 35574, 35575, 35576, 35577, 35578, 35579, 35580, 35581, 35582, 35583, 35584, 35585, 35586, 35587, 35588, 35589, 35706, 35707, 35708, 35709, 35710, 35711, 35712, 35713, 35714, 35715, 35716, 35717, 35718, 35719, 35720";
        String[] split = s.trim().split(",");
        String[] split2 = s2.trim().split(",");
        List<String> list = Arrays.asList(split);
        List<String> list2 = Arrays.asList(split2);
        System.out.println(list.size());
        System.out.println(list2.size());
    }
}