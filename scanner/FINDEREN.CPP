#include<stdio.h>
#include<dir.h>
#include<conio.h>
#include<stdlib.h>
#include<string.h>
char *nam,*cmd,che;
char cmds[5][10]={"help","search","display"};
int done;
char curdir[MAXPATH];

void av_chk(char *FILE);
int path(void);
char* cur_dir(char *path);
void quick_search(void);
void file_disp();
void help(void);
void cmd_gen(void);


void main(void)
{
	clrscr();
	printf("Welcome to quick search engine\n");
	printf("For help on commands please type help at command line\n");

	do
	{
	cmd_gen();
	che = getch();
	while(che=='q'||che=='Q')
	{  exit(1);}
	switch(che)
	{
		case 'S':
		case 's':

			quick_search();
			flushall();
			break;

		case 'D':
		case 'd':

			file_disp();
			flushall();
			break;

		case 'H':
		case 'h':

			help();
			break;
		case '\x0D':
			printf("\n");
			break;

		default:

			printf("error\n");
			break;


	}

	}while(che!='q'||che!='Q');
	fcloseall();

}

void quick_search(void)
{
	clrscr();
	struct ffblk ffblk;
	char hid;
	int done,i=0,at=0,d=0;
	printf("Directory listing of *.*\n");
	printf("Enter filename with extension or use wild cards (name.ext)\n");
	gets(nam);
	printf("search in hidden files and folders?y/n)\n(Recommended for deep scanning)");
	hid = getche();
	switch(hid)
	{
		case 'y':
		case 'Y':

		       at=2;
		       break;

		case 'n':
		case 'N':

		       at=1;
		       break;

		default:

		       printf("\ninvalid selection\n");
		       getch();
		       exit(1);

	 }
//	chdir("C:\Documents and Settings\VHMIB\Desktop");
	done = findfirst(nam,&ffblk,at);
	while (!done)
		{

			d++;
			if(d>40)
				{
					printf("preass any key to  continue . . . .");
					d=0;
					getch();
				}
			printf("\n  %s", ffblk.ff_name);
			av_chk(ffblk.ff_name);
			done = findnext(&ffblk);
//			chdir("C:\Documents and Settings\VHMIB\My Documents");
			i++;
		}

	cur_dir(curdir);
	printf("\n\n%d file(s) are in the directory  %s\n",i, curdir);
	getch();
	flushall();
	exit(1);


}
char* cur_dir(char *path)
{
	   strcpy(path, "X:\\");      /* fill string with form of response: X:\ */
	   path[0] = 'A' + getdisk();    /* replace X with current drive letter */
	   getcurdir(0, path+3);  /* fill rest of string with current directory */
	   return(path);
}
void file_disp(void)
{
	clrscr();
	FILE *fp;
	int d=0;
	char b,ch,*nam2;
	printf("display searched file or new?(Searched/New)");
	b=getche();
	switch(b)
	{
		case 'S':
		case 's':
			if((fp = fopen(nam,"r"))== NULL)
				{
				printf("error in file accessing");
				exit(1);
				}
			break;
		case 'N':
		case 'n':
			printf("\nNew file name?");
			gets(nam2);
			if((fp = fopen(nam2,"r"))== NULL)
				{
				printf("error in file accessing");
				getch();
				exit(1);
				}
			 break;
		default:
			printf("invalid");
			getch();
			exit(1);
	}
	while((ch=getc(fp))!=EOF)
	{
		d++;
		if(d>1000)
			{
				printf("\n\npress any key to contniue . . . .");
				d=0;
				getch();
			}
		printf("%c",ch);

	}
	printf("\n\n");
	fclose(fp);
	getch();
}

void help(void)
{
	clrscr();
	printf("\nType any of these commads at the command line to initialize\nthe respective program\n");
	printf("\n\nFor searching files and documents,\t's'\n");
	printf("\n\nFor displaying part of doc. for verification, \t'd'\n\n");
}

void cmd_gen(void)
{
	for(;;)
	{
		cur_dir(curdir);
		printf("%s:",curdir);
		//gets(cmd);
		if(cmd!="\0")
			break;

	}
}

void av_chk(char *fl)
{
	FILE *fa;
	char *vir;
	fa = fopen("Vdef.txt","r");
	int i;
	do
	{
	for(i=0;;i++)
	{
		vir[i] = getc(fa);
		if(vir[i]==EOF)
			break;
		if(vir[i]=='-')
			{
			vir[i]='\0';
			break;
			}
	}
	int d = strcmp(fl,vir);
			if(d==0)
				printf("\t\t\tVIRUS ALERT : %s",fl);
	}while(i);
	fclose(fa);
	fcloseall();


}





