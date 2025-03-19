#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>

struct Flags {
	unsigned int isVisible : 1; // 1비트
	unsigned int isActive : 1; // 1비트
	unsigned int isDeleted : 1; // 1비트
	unsigned int type : 2; // 2비트
};
int main() {
	struct Flags flag = { 1,0,0,2 }; // 초기화

	printf("isVisible: %u\n", flag.isVisible);
	printf("isActive: %u\n", flag.isActive);
	printf("isDeleted: %u\n", flag.isDeleted);
	printf("type: %u\n", flag.type);

	return 0;
}