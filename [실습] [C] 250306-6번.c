#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>

int main() {
	char gender;
	int age;
	double height;
	printf("성별은?(남자라면 M, 여자라면 F) >>");
	gender = getchar();

	printf("\n나이는? >>");
	scanf_s("%d", &age);

	printf("\n키는? >>");
	scanf_s("%lf", &height);

	printf("\n====================\n");
	printf("성별 : %c\n", gender);
	printf("나이 : %3d\n", age);
	printf("키 : %.1lf\n", height);
	printf("====================\n");
	

	char name[10]; // 배열은 같은 자료형을 연속된 공간에 저장
	printf("이름은? ");
	// 문자열 : 한글자 이상 문자열
	//gets(name);
	//puts(name);
	scanf_s("%s", name, 10);
	printf("\n%s\n", name);
	
	int a;
	int b;
	printf("정수를 2개 입력하세요 >>");
	scanf_s("%d %d", &a, &b);
	printf("\n%d %d", a, b);
}