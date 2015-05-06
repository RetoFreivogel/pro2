function [pg_gs] = get_pg_gs(gs)

% Diese Funktion liefert den Phasengang der Strecke, welche mit der 
% Übertragungsfunktion gs mitgegeben wird.
% Berechnung phasengang Strecke
%
% Variablenbeschreibung:
% pg_gs   Array mit phase der Übertragungsfunktion der Strecke
for k=1:length(gs)                             %Schleife: für jedes element von gs wird winkel berechnet
    if real(gs(k))>0                           %fallunterscheidung 1: realteil>0, keine speziellen anforderungen zur winkelberechnung
        pg_gs(k)= angle(gs(k))/pi*180;
    end
    if real(gs(k))<0                           %fallunterscheidung 2: realteil<0 winkelberechnung muss beachtet werden / angle() liefert zwar richtigen winkel aus richtigem quadranten, aber nimmt immer einen wert dessen betrag<180 ist bsp: 170° statt -190°
        if imag(gs(k))<0                       %im dritten quadranten keine probleme, normale berechung
            pg_gs(k)= (angle(gs(k))/pi*180);
        end
        if imag(gs(k))>0
            pg_gs(k)= (angle(gs(k))/pi*180)-360;    %im 2ten quadranten muss 360° abgezogen werden, da angle() z.b. 170° statt -190° nimmt
        end
    end
end

end

