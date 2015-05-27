function [tnpi, krpi,T] = PiReglerDim(Tu,Tg,Ks,p_rand)
% Dimenioniert die Tn (Nachstellzeit), sowie Kr (Verstärkungsfaktor) Werte 
% des PI-Reglers mithilfe der Phasengangmethode, anhand der eingegebenen
% Tu, Tg- Werte der Schrittantwort der Strecke und dem gewünschten
% Phasenrand.
% w             Vektor mit gespeicherten Kreisfrequenzen
% pg_gs         Phasengang der Strecke


[n,T] = p2_sani(Tu,Tg);     %bringt Orgnung n und Zeitonstanten in T der Strecke
[gs,w] = get_gs(T,Ks);         %liefert die Übertragungsfunktion der Strecke in gs und Vektor w
[pg_gs] = get_pg_gs(gs);    %liefert Phasengang der Strecke
[index] = find_index(pg_gs, -90);       %liefert den index (im Array) der Kreisfrquenz an Stelle -90° des Phasengangs der Strecke
wpi=w(index);
tnpi=1/wpi;                  % tnpi      Nachstellzeit Tn des PI-Reglers


% Berechnung KRpi
% Variablenbeschreibung
% pg_gr_pi      Phasengang des PI-Reglers
% pg_go   Phasengang der Regelung (Addition des Strecken- und Reglerphasengangs)
% delta_wdpi    Hilfsvariable zur Erkennung wann wdpi gefunden
% wdpi          Kreisfrequenz, wo die Gesamtverstärkung der Regelung 1 sein
%               sollte
% gspi          Amplitude der Strecke an Stelle wdpi speichern
% grpi_ganz     kompletter Amplitudengang des PI-Reglers
% gopi          Amplitude des PI Reglers an Stelle wdpi  
% grpi          Amplitude des Reglers an Stelle wdpi
% krpi          Verstärkungsfaktor des PI-Reglers
% p_rand        gewünschter Phasenrand
pg_gr_pi=(atan(w*tnpi)-pi/2)/pi*180; 
pg_go=pg_gr_pi+pg_gs;

[index] = find_index(pg_go, (-180+p_rand));
wdpi=w(index);               %liefert den index (im Array) der Kreisfrquenz an Stelle des Phasenrands des Phasengangs der Regelung


gspi=abs(gs(index));             %Amplitude der Strecke an Stelle wdpi speichern
grpi=sqrt(1+(wdpi*tnpi)^2)./(wdpi*tnpi);    %Amplitude des PI Reglers an Stelle wdpi
gopi=gspi*grpi;                         %Amplitude der Reglung an Stelle wdpi
krpi=1/gopi;                        %Verstärkungsfaktor krpi

end