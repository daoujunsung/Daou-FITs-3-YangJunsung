#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>

typedef void (*operation4)(int[3][3]);
void printArrSum(int[3][3]);
void printArrMax(int[3][3]);
void printArrMin(int[3][3]);
void printArrSquared(int[3][3]);

int main() {
	int choice;
	int arr[3][3] = { {0,1,2},{3,4,5},{6,7,8} };
	operation2 operations[4] = { printArrSum, printArrMax,printArrMin, printArrSquared };
	do {
		printf("연산 방법을 선택하기\n(0: 합, 1 : 최대값, 2 : 최소값, 3 : 제곱, 4: 중단) : ");
		scanf_s("%d", &choice);

		if (choice < 0 || choice > 4) {
			printf("잘못된 선택입니다.\n");
		}
		else if (choice == 4) printf("종료합니다.\n");
		else {
			operations[choice](arr);
		}
	} while (choice != 4);

	return 0;
}

void printArrSum(int arr[3][3]) {
	int sum = 0;
	for (int i = 0; i < 3; i++) {
		for (int j = 0; j < 3; j++) {
			sum += *(*(arr + i) + j);
		}
	}
	printf("배열의 합은 %d\n\n", sum);
}

void printArrMax(int arr[3][3]) {
	int max = -999;
	for (int i = 0; i < 3; i++) {
		for (int j = 0; j < 3; j++) {
			if (*(*(arr + i) + j) > max) max = *(*(arr + i) + j);
		}
	}
	printf("배열의 최대값은 %d\n\n", max);
}

void printArrMin(int arr[3][3]) {
	int min = 999;
	for (int i = 0; i < 3; i++) {
		for (int j = 0; j < 3; j++) {
			if (*(*(arr + i) + j) < min) min = *(*(arr + i) + j);
		}
	}
	printf("배열의 최소값은 %d\n\n", min);
}

void printArrSquared(int arr[3][3]) {
	printf("배열의 제곱값은\n");
	for (int i = 0; i < 3; i++) {
		for (int j = 0; j < 3; j++) {
			printf("%d ", (*(*(arr + i) + j)) * (*(*(arr + i) + j)));
		}
		printf("\n");
	}
	printf("\n");
}
