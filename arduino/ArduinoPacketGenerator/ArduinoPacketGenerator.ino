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

void setup() {
  //Begins a serial communication
  Serial.begin(115200);
  pinMode(A0, INPUT);
  pinMode(A1, INPUT);
  pinMode(A2, INPUT);
  pinMode(A3, INPUT);
  pinMode(5, OUTPUT);
}

const char readSample(unsigned int port)
{
  const int val = analogRead(port);
  const char ret = map(val, 0, 1024, -127, 128);
  return ret;
}

#define PACKET_SIZE 1000

void loop() {

  while(Serial.available() < 1);
  const char in = Serial.read();

  const int channel = [&](char input){
    int ret = A0;
    switch(input)
    {
      case '0':
        ret = A0;
        break;
      case '1':
        ret = A1;
        break;
      case '2':
        ret = A2;
        break;
      case '3':
        ret = A3;
        break;
    }
    return ret;
  }(in);

  Serial.flush();

  char packet[PACKET_SIZE];
  for(unsigned int i = 0; i < PACKET_SIZE; i++)
  {
   packet[i] = readSample(channel);
  }

  for(int i = 0; i < PACKET_SIZE; i++)
  {
    Serial.print((char) packet[i]);
  }
}
