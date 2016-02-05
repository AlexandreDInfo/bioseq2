CC = gcc
CFLAGS = -Wall -Werror -ansi -pedantic
CFLAGS += -D_XOPEN_SOURCE=500
CFLAGS += -g
LDFLAGS = -g
EXE = rendu-tp-1

all : realclean ${EXE}

rendu-tp-1 : rendu-tp-1.o
	${CC} ${LDFLAGS} -o $@ $^

rendu-tp-1.o: rendu-tp-1.c
	${CC} ${CFLAGS} -c $<

clean:
	rm -rf *.o *~ || true

realclean: clean
	rm -rf ${EXE}
