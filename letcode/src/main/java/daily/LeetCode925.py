# 由于键盘不灵敏可能导致一些字符重复键入，忽略重复键入的情况做字符串判等
class Solution:
    def isLongPressedName(self, name: str, typed: str) -> bool:
        if len(name) == 0:
            return len(typed) == 0
        if len(typed) == 0:
            return False
        return name[0] == typed[0] and self.doIsLongPressedName(name, typed, 1, 1)
    def doIsLongPressedName(self, name: str, typed: str, iName:int, iTyped:int) -> bool:
        # if(len(name))
        return (name[iName] == typed[iTyped] and self.doIsLongPressedName(name,typed,iName+1,iTyped+1)) or (typed[iTyped - 1] == typed[iTyped] and self.doIsLongPressedName(name,typed,iName,iTyped+1))
        # # 键入的一定不会比原字符短
        # distance = len(typed) - len(name)
        # if distance < 0 or name[0] != typed[0]:
        #     return False
        #
        # iName = 0
        # iTyped = 0
        # while True:
        #     # 找到第一个不匹配处
        #     while iName < len(name) and iTyped < len(typed) and name[iName] == typed[iTyped]:
        #         iName = iName + 1
        #         iTyped = iTyped + 1
        #     # 查看是不是匹配完成了
        #     if iName >= len(name) and iTyped >= len(typed):
        #         return True
        #     # 剩下的都是重复键入
        #     if iName >= len(name):
        #         while iTyped < len(typed):
        #             if name[iName-1] != typed[iTyped]:
        #                 return False
        #             iTyped = iTyped + 1
        #         return True
        #
        #     # 查看是否是重复键入的问题，如果是则消耗一个字符的差距
        #     if distance > 0 and typed[iTyped] == typed[iTyped - 1]:
        #         distance = distance - 1
        #         iTyped = iTyped + 1
        #     else:
        #         return False


print(Solution().isLongPressedName("","aazlllllllllllllleexxxxxxxxxxxxxxxya"))
