#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>

int main() {
	int rowNum, colNum, delRow;
	printf("행과 열의 수를 입력하세요: ");
	scanf_s("%d %d", &rowNum, &colNum);
	int arr[5][5];
	for (int i = 0; i < rowNum; i++) {
		for (int j = 0; j < colNum; j++) {
			*(*(arr + i) + j) = 10 * i + j;
			printf("%2d ", *(*(arr + i) + j));
		}
		printf("\n");
	}
	printf("삭제하려는 행의 인덱스 번호를 입력하세요? ");
	scanf_s("%d", &delRow);

	for (int i = delRow; i < rowNum - 1; i++) {
		for (int j = 0; j < colNum; j++) {
			arr[i][j] = arr[i + 1][j];
		}
	}

	for (int j = 0; j < colNum; j++) {
		arr[rowNum - 1][j] = 0;
	}

	// 수정된 배열 출력
	printf("수정된 배열 출력\n");
	for (int i = 0; i < rowNum-1; i++) {
		for (int j = 0; j < colNum; j++) {
			printf("%2d ", arr[i][j]);
		}
		printf("\n");
	}

	return 0;
}
