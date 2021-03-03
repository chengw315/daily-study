# 字符串判等（字符串中含有#，代表删除上一个有效字符）
# 关键点： 数组  元素关系   当前元素如果是“#”，会影响前面的元素
# |||||||||||||||||||||||||||||||||||||
# | a | b | c | d | # | a | b | # | f |
# |||||||||||||||||||||||||||||||||||||
#                <--         <--
#                删除        删除
# 数组的优点：可以正向遍历、也可以倒序遍历、也可以中间到两边、还可以两边到中间
# 对于这种后面的元素影响着前面的元素的数组，倒序遍历可以方便的处理这种关系

class Solution:
    def backspaceCompare(self, S: str, T: str) -> bool:
        # 当前遍历的下标
        iS = self.find(S, len(S))
        iT = self.find(T, len(T))
        # 不断找倒数第一个有效的字符对比，直至其中一个到头
        while iS >= 0 and iT >= 0 and S[iS] == T[iT]:
            iS = self.find(S, iS)
            iT = self.find(T, iT)
        return iS == -1 and iT == -1

    # 找到倒数第一个有效字符的下标
    def find(self, S: str, last: int) -> int:
        # ‘#’的计数
        delNumS = 0
        i = last - 1
        while i >= 0:
            if S[i] == '#':
                delNumS = delNumS + 1
            elif delNumS > 0:
                delNumS = delNumS - 1
            else:
                return i
            i = i - 1
        return -1

print(Solution().backspaceCompare("ab#c", "ad#c"))
