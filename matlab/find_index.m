function [index1] = find_index(pg, value)
% Diese Funktion liefert den Index, an dessen Stelle pg den Wert Value hat.
%
% Variablenbeschreibung
% value       Eingegebener Phasenpunkt (bei PI-Regler = -90° / bei PID-Regler = -135°)
% index1/index2      gespeicherte Indexwerte / beide für Suche benötigt
% delta_index   Differenz zwischen index1 und index2


index1 = round(length(pg)/2);
index2=length(pg);
for i=1:18    
    delta_index=norm(index2-index1);
    if pg(index1) < value
        index2=index1;
        index1=index1-floor(delta_index/2);
    else
        index2=index1;
        index1=index1+round(delta_index/2);  
    end
end

end
