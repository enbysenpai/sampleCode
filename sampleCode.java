for(int i = 0;i<N;i++)
        {
            audioData[i] = audioDataBuffer.get(i)/32768.0;
        }
        for(int i=0;i<N;i++)
        {
            double hammingWindow=0.54 - 0.46*Math.cos((2.0*Math.PI*i)/(N-1));
            audioData[i]=audioData[i]*hammingWindow;
        }

        for(int i = N;i<P;i++)
        {
            audioData[i] = 0;
        }

        fft1D.realForward(audioData);

        double[] magnitudes=new double[P/2];
        for (int freq=0;freq<P/2;freq+=2)
        {
            double real=audioData[freq];
            double imag=audioData[freq+1];
            magnitudes[freq]=Math.sqrt(real*real + imag*imag);
        }

        int maxMagn=0;
        for(int i=0;i<magnitudes.length;i++)
        {
            if(magnitudes[i]>magnitudes[maxMagn])
            {
                maxMagn=i;
            }
        }