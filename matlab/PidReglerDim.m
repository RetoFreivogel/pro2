function [beta, tnk, tvk, krk, tnpid, tvpid, krpid, tppid,T] = PidReglerDim(Tu, Tg, Ks, p_rand)
% Dimensioniert die Tnk und Tvk-Werte, sowie Krk (Verstärkungsfaktor) Werte 
% des PID-Reglers mithilfe der Phasengangmethode, anhand der eingegebenen
% Tu, Tg- Werte der Schrittantwort der Strecke und dem gewünschen
% Phasenrand.
%
%
% Variablenbeschreibung:
% abl_wpid       Ableitung des Phasengangs der Strecke bei Kreisfrequenz wpid
% subst          Substitutionsvariable um Beta zu berechnen

tic
[n,T] = p2_sani(Tu,Tg);     %bringt Ordnung n und Zeitkonstanten in T der Strecke
[gs,w] = get_gs(T,Ks);      %liefert die Übertragungsfunktion der Strecke in gs und Vektor w
[pg_gs] = get_pg_gs(gs);    %liefert Phasengang der Strecke
[index]=find_index(pg_gs,-135); %liefert den index (im Array) der Kreisfrquenz an Stelle -135° des Phasengangs der Strecke
wpid=w(index);

abl_wpid=0;       
for k=1:length(T)
    abl_wpid=abl_wpid - T(k)/(1+wpid^2*T(k)^2); % Berechnet Ableitung
end

subst= -0.5 - abl_wpid*wpid;      %Substitutionsvariable um Beta zu berechnen
beta1=1/subst+sqrt(1/subst^2-1);
beta2=1/subst-sqrt(1/subst^2-1);

if imag(beta1)~=0 || abs(beta1)>1 || beta1<0
    beta1=1;
end
beta=beta1;
if imag(beta2)==0 && abs(beta2)<1 && beta2>0
    beta=beta2;
end

tnk=1/(beta*wpid);
tvk=beta/wpid;
tppid=tvk/10;

% Berechnung Krk
% Variablenbeschreibung
% pg_gr_pid      Phasengang des PID-Reglers
% pg_go         Phasengang der Regelung (Addition des Strecken- und Reglerphasengangs)
% wdpid          Kreisfrequenz, wo die Gesamtverstärkung der Regelung 1 sein
%               sollte
% gspid          Amplitude der Strecke an Stelle wdpid speichern
% gopid          Amplitude des PID Reglers an Stelle wdpid  
% grpid          Amplitude des Reglers an Stelle wdpid
% krk          Verstärkungsfaktor des PID-Reglers
% p_rand        gewünschter Phasenrand
pg_gr_pid = (atan(w*tnk) + atan(w*tvk) - pi/2 - atan(w*tppid)) /pi*180; 
pg_go = pg_gr_pid + pg_gs;

[index] = find_index(pg_go, (-180+p_rand));
wdpid = w(index);      %liefert den index (im Array) der Kreisfrquenz an Stelle des Phasenrands des Phasengangs der Regelung
        
%Verstärkungsfaktor krk
gspid = abs(gs(index));
grpid=sqrt(1+(wdpid*tnk)^2)*sqrt(1+(wdpid*tvk)^2)/(wdpid*tnk);    %Amplitude des PID Reglers an Stelle wdpi
gopid=gspid*grpid;           %Amplitude der Reglung an Stelle wdpi
krk=1/gopid;            %Verstärkungsfaktor krk

%Umrechnung in reglerkonform
[tnpid, tvpid, krpid] = Umrechnung_bk_rk(tvk, tnk,tppid, krk);

toc

end

