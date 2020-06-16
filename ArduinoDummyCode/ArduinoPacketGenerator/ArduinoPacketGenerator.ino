/*
    This file is part of softOscilloscope.

    softOscilloscope is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    softOscilloscope is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with softOscilloscope.  If not, see <https://www.gnu.org/licenses/>.
*/
#include <math.h>
#define WORK (2*M_PI*i/100)

static char samples[1000];

void setup() {
  //Begins a serial communication
  Serial.begin(9600);
  pinMode(A0, INPUT);
  pinMode(5, OUTPUT);
}

const char readSample()
{
  const int val = analogRead(A0);
  const char ret = map(val, 0, 1024,-127, 128);
  return ret;
}

void loopDummy()
{
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

void loopRead()
{
  char *const pointer = samples;
  for(int i = 0; i < 1000; i++)
  {
    analogWrite(5, (100 * sin(WORK)) + 2);
    pointer[i] = readSample();
  }

  for(int i = 0; i < 1000; i++)
  {
    Serial.print((char)pointer[i]);
  }
  delay(100);
}

void loop() {
  loopRead();
}
