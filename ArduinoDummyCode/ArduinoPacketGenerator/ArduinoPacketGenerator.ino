#include <math.h>

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
}

void loop() {
  // put your main code here, to run repeatedly:
  char dummySamples[1000];
  for(int i = 0; i < 1000; i++)
  {
    double work = 2*M_PI*i/100;
    dummySamples[i] = (10 * sin(work)) + 2;
    Serial.print((char)dummySamples[i]);
  }
  delay(1000);
}
