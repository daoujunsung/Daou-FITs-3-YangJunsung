#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>

void circle();
void evenSum();
int main() {
	
	circle();
	evenSum();

	return 0;
}

void circle() {
	double pi = 3.1415926535;
	int menu = 0;
	int r;
	do {
		printf("=========================\n");
		printf("== 1. 원의 둘레 구하기 ==\n");
		printf("== 2. 원의 넓이 구하기 ==\n");
		printf("== 3. 구의 부피 구하기 ==\n");
		printf("== 4. 그만두기         ==\n");
		printf("=========================\n");
		printf("원하는 내용은? ");
		scanf_s("%d", &menu);
		if (menu < 4) {
			printf(">>반지름은? ");
			scanf_s("%d", &r);
		}
		switch (menu) {
		case 1: printf(">>반지름이 %d인 원의 둘레는 %.2lf\n", r, 2 * pi * r); break;
		case 2: printf(">>반지름이 %d인 원의 넓이는 %.2lf\n", r, pi * r * r); break;
		case 3: printf(">>반지름이 %d인 구의 부피는 %.2lf\n", r, 4 * pi * r * r * r / 3); break;
		}
	} while (menu < 4);
}

void evenSum() {
	int n;
	printf("정수 n을 입력: ");
	scanf_s("%d", &n);

	printf("정수 1에서 %d 이하 짝수들의 합은 %d입니다.", n, (n/2)*(n/2+1));
}