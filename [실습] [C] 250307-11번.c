#include <stdio.h>

void calculator();
int plus(int a, int b);
int minus(int a, int b);
int times(int a, int b);
double divide(int a, int b);

void timestable();

void letterChange();

int main() {
	
	calculator();
	timestable();
	getchar();
	letterChange();

	return 0;
}

void calculator() {
	int a, b;
	printf("가감승제를 원하는 두 수를 입력하세요 : ");
	scanf_s("%d %d", &a, &b);
	printf("%d + %d = %d\n", a, b, plus(a, b));
	printf("%d - %d = %d\n", a, b, minus(a, b));
	printf("%d * %d = %d\n", a, b, times(a, b));
	printf("%d / %d = %lf\n", a, b, divide(a, b));

}

int plus(int a, int b) {
	return a + b;
}

int minus(int a, int b) {
	return a - b;
}

int times(int a, int b) {
	return a * b;
}

double divide(int a, int b) {
	return a * 1.0 / b;
}

void timestable() {
	int n;
	printf("출력을 원하는 단은: ");
	scanf_s("%d", &n);

	for (int i = 1; i < 10; i++) {
		printf("%d x %d = %d\n", n, i, n * i);
	}
}

void letterChange() {
	char word[10];
	printf("문자열 입력?");
	gets(word);
	printf("변환된 결과 ");
	for (int i = 0; i < sizeof(word); i++) {
		if (word[i] >= 'a' && word[i] <= 'z') {
			printf("%c", word[i] - ('a' - 'A'));
		} else if (word[i] >= 'A' && word[i] <= 'Z') {
			printf("%c", word[i] + ('a' - 'A'));
		}
		else {
			printf(" ");
		}
	}
	
}