function [gs,w] = get_gs(T,Ks)

% liefert anhand der Zeitkonstanten, die in T gespeichert sind, die Übertra-
% gungsfunktion der Strecke
% Im Vektor w werden die Kreisfrequenzen gespeichert, die zur Simulation, 
% und Berechnung der Phasen- sowie Amplitudengänge verwendet werden
%
% Variablenbeschreibung:
% w         Kreisfrequenzen
% T()       Zeitkonstanten, die von p2_sani generiert wurden
% zwires    Zwischenresultat bei der Berechnung des Nenners
% nennerGs  Nenner der Übertragungsfunktion der Strecke
% gs        Übertragungsfunktion der Strecke

%w=linspace(1/(T(length(T))*10), 10/T(1), 10000);  % variable Omegas, deckt gesamte breite ab
w=logspace(log10(1/(T(length(T))*10)),log10(10/T(1)),10000000); % Variable Omegas, deckt gesamte Breite ab, Punkteverteilung logarithmisch zur besseren Auflösung

nennerGs = (1+i*w*T(1)).*(1+i*w*T(2));    %nenner der Übertragungsfunktion der Strecke wird mit berechnet
for k=3:length(T)
    zwires=nennerGs.*(1+i*w*T(k));
    nennerGs=zwires;
end
gs=Ks*1./nennerGs;                               %Übertragungsfunktion der Strecke definieren (eigentlich Frequenzgang)

end

