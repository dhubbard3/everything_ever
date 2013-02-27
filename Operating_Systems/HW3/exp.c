#include <stdio.h>
#include <stdlib.h>
#include <math.h>

float urand()
{
  return( (float) rand()/RAND_MAX );
}

float genexp(float lambda)
{
  float u,x;
  u=urand();
  x=(-1/lambda)*log(u);
  return(x);
}



void main(int argc, char *argv[])
{
  float e,lambda;
  int i,n;
  n=atoi(argv[1]);
  lambda=atof(argv[2]);
  for (i=0;i<n;i++)
    {
      e=genexp(lambda);
      printf(" %2.4f \n",e);
    }
}



float ran1(long *idum)
{
int j;
long k;
static long iy=0;
static long iv[NTAB];
float temp;
if (*idum <= 0 || !iy) {
if (-(*idum) < 1) *idum=1;
else *idum = -(*idum);
for (j=NTAB+7;j>=0;j--) {
k=(*idum)/IQ;
*idum=IA*(*idum-k*IQ)-IR*k;
if (*idum < 0) *idum += IM;
if (j < NTAB) iv[j] = *idum;
}
iy=iv[0];
}
k=(*idum)/IQ;
*idum=IA*(*idum-k*IQ)-IR*k;
if (*idum < 0) *idum += IM;
j=iy/NDIV;
iy=iv[j];
iv[j] = *idum;
if ((temp=AM*iy) > RNMX) return RNMX;
else return temp;
}

