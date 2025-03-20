#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <errno.h>
#include <string.h>

int main() {
	FILE* f = fopen("D:\\TestFile.txt", "wb+");
	char str[128];
	if (f) {
		fputs("0123456789\r\n0123456789", f);
		fseek(f, 0, SEEK_SET);
		fgets(str, sizeof(str), f);
		printf("read: %s", str);

		fclose(f);
	}
	else printf("Error: %d, %s", errno, strerror(errno));
}