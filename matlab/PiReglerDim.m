function [tnpi, krpi,T] = PiReglerDim(Tu,Tg,Ks,p_rand)
% Dimenioniert die Tn (Nachstellzeit), sowie Kr (Verst�rkungsfaktor) Werte 
% des PI-Reglers mithilfe der Phasengangmethode, anhand der eingegebenen
% Tu, Tg- Werte der Schrittantwort der Strecke und dem gew�nschten
% Phasenrand.
% w             Vektor mit gespeicherten Kreisfrequenzen
% pg_gs         Phasengang der Strecke


[n,T] = p2_sani(Tu,Tg);     %bringt Orgnung n und Zeitonstanten in T der Strecke
[gs,w] = get_gs(T,Ks);         %liefert die �bertragungsfunktion der Strecke in gs und Vektor w
[pg_gs] = get_pg_gs(gs);    %liefert Phasengang der Strecke
[index] = find_index(pg_gs, -90);       %liefert den index (im Array) der Kreisfrquenz an Stelle -90� des Phasengangs der Strecke
wpi=w(index);
tnpi=1/wpi;                  % tnpi      Nachstellzeit Tn des PI-Reglers


% Berechnung KRpi
% Variablenbeschreibung
% pg_gr_pi      Phasengang des PI-Reglers
% pg_go   Phasengang der Regelung (Addition des Strecken- und Reglerphasengangs)
% delta_wdpi    Hilfsvariable zur Erkennung wann wdpi gefunden
% wdpi          Kreisfrequenz, wo die Gesamtverst�rkung der Regelung 1 sein
%               sollte
% gspi          Amplitude der Strecke an Stelle wdpi speichern
% grpi_ganz     kompletter Amplitudengang des PI-Reglers
% gopi          Amplitude des PI Reglers an Stelle wdpi  
% grpi          Amplitude des Reglers an Stelle wdpi
% krpi          Verst�rkungsfaktor des PI-Reglers
% p_rand        gew�nschter Phasenrand
pg_gr_pi=(atan(w*tnpi)-pi/2)/pi*180; 
pg_go=pg_gr_pi+pg_gs;

[index] = find_index(pg_go, (-180+p_rand));
wdpi=w(index);               %liefert den index (im Array) der Kreisfrquenz an Stelle des Phasenrands des Phasengangs der Regelung


gspi=abs(gs(index));             %Amplitude der Strecke an Stelle wdpi speichern
grpi=sqrt(1+(wdpi*tnpi)^2)./(wdpi*tnpi);    %Amplitude des PI Reglers an Stelle wdpi
gopi=gspi*grpi;                         %Amplitude der Reglung an Stelle wdpi
krpi=1/gopi;                        %Verst�rkungsfaktor krpi


%-------------------------------------------------------------------------
%Folgendes nur f�r Darstellung, nicht ben�tigt f�r Reglerdimensionierung!!
%-------------------------------------------------------------------------

% Berechnung Amplitudengang Strecke
ampgs = 20*log10(abs(gs));
% Berechnung Amplitudengang Regler
grpi_ganz=(1+i*w*tnpi)./(i*w*tnpi);     %kompletter Amplitudengang des PI Reglers 
ampgrpi_ganz=20*log10(abs(grpi_ganz));       %nur f�r darstellung

%Darstellung berechnungsresultate
figure(2);
subplot(212)                    %plot f�r phasengang
semilogx(w, pg_gs, w, pg_go), grid on
legend('Phaseng. Strecke', 'Phaseng. offene Regelung')
title('Phasengang');
xlabel('variable Frequenz w')
ylabel('Phase �')
axis([1e-2 1e2 -270 0])

subplot(211)                    %plot f�r amplitudengang
semilogx(w, ampgs,w, ampgrpi_ganz), grid on
legend('Amp.g. Strecke', 'Amp.-g. Regler')
title('amplitudengang');
xlabel('variable Frequenz w')
ylabel('Amplitude [dB]')
axis([1e-2 1e2 -150 30])

end

