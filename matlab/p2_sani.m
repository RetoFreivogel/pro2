function [n,T] = p2_sani(tu,tg,p)
%ccccc Sani-Approximation einer Schrittantwort.
%      [n,T] = hudzo(Tu,Tg,p) liefert den Grad n und die Zeit-
%      kostanten T (Vektor) zurueck (n x PT1).
%      Schrittantwort mit Wendetangente vermessen:
%      Tu und Tg sind Verzugszeit und Anstiegszeit
%      der Schrittantwort.
%      Wird ein Wert für p eingegeben, so erfolgt
%      auch ein Plot der Schrittantworten

if (nargin<2)
  error('!!!! Bitte 2 (ZWEI) Zeiten eingeben !!!!');
end;

if (nargin > 2)          
    p=1;     % Mit Plots der Schrittantworten
else
    p=0;     % Ohne Plots
end;

if tu<=0 || tg<=0 
  disp(' ');
  error('!!!!! unsinnige Zeiten !!!!!');
end;

v=tu/tg;
if v>0.64173
  disp(' ');
  error('!!!!! Tu/Tg zu gross --> N > 8  !!!!!');
end;

if v<0.001
  disp(' ');
  error('!!!!! Tu/Tg zu klein --> N = 1  !!!!!');
end;

load('p2_sani_tu_tg');
pause(0.1);                   % Pause, damit Laden vom File erfolgreich!!!!

ri=linspace(0,1,50);
% Berechnet mit NN=50 (r-Auflösung)

if v <= 0.103638		% abhaengig von n werden vorberechnete
  n=2;				% Datenfiles von der Festplatte geladen.  
elseif v <= 0.218017		% 2 <= n <= 8
  n=3;				% n=1 ist trivial und fuehrt zu Abbruch  
elseif v <= 0.319357
  n=4;  
elseif v <= 0.410303
  n=5;  
elseif v <= 0.4933
  n=6;  
elseif v <= 0.5700
  n=7;  
elseif v<=0.64173
  n=8;  
else
  n=10;
end;

r=spline(Tu_Tg(n,:),ri,v);
w=spline(ri,T_Tg(n,:),r);
T(n)=w*tg;


for i=n-1:-1:1,                 % Umspeicher, damit gleiche Reihenfolge wie bei Hudzovik
  T(i)=T(n)*r^(n-i);
end;

% Plots der Schrittantworten
if p==1    
    TT=4*(tg);
    t=linspace(0,TT,2500);
    za=1;
    n1=conv([T(1) 1],[T(2) 1]);
    for k=3:n
        nen1=conv(n1,[T(k) 1]);  
        n1=nen1;  
    end;
    nens=n1;
    step(za,nens,t); grid on;
    hold on;
    wendepkt(T);
    hold off;
end;


