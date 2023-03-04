# Problem
palindrome 인지 판별하여라.

# solution
- 모든 문자를 `toLowerCase()`하고, RegEx를 통해 알파벳이 아닌 문자를 제거한다. `replace(/[^a-z]/g)`
	- RegEx에서 ^는 a-z가 아닌 값들을 의미하고, `[^a-z0-9]`는 알파벳과 숫자를 남기게된다.

- 문자열을 뒤집는 방법은 `string.split('').reverse().join('')`

