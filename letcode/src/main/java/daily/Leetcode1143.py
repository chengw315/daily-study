#最长公共子序列
class Solution:
    def longestCommonSubsequence(self, text1: str, text2: str) -> int:
        result = 0
        for i in range(len(text1) - 1):
            dfa = self.dfa(text1[i:len(text1)])
            state = 0
            for j in range(len(text2)):
                state = dfa[state][ord(text2[j]) - ord('a')]
                result = max(result, state)
                if state == len(text2):
                    break
        return result

    def dfa(self, pattern: str) -> list:
        dfa = [[0] * 26 for i in range(len(pattern))]
        dfa[0][ord(pattern[0]) - ord('a')] = 1
        X = 0
        for i in range(1, len(pattern)):
            for j in range(26):
                dfa[i][j] = dfa[X][j]
            dfa[i][ord(pattern[i]) - ord('a')] = i + 1
            X = dfa[X][ord(pattern[i]) - ord('a')]
        return dfa


result = Solution.longestCommonSubsequence(Solution(), "banana", "banana")
print(result)
