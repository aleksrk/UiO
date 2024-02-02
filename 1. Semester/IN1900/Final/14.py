def is_palindrome(word):

    wordbackwards = word[::-1]
    
    if wordbackwards == word:
        return True
    else:
        return False

s = "racecar"
b = "not"

ans = is_palindrome(s)

print(ans)
print(is_palindrome(b))
