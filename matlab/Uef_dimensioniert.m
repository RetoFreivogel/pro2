function [ ] = Uef_dimensioniert(Tu,Tg,Ks,p_rand)
tic
% stellt die Übertragungsfunktion der dimensionierten Regelung dar.


[tnpi, krpi,T] = PiReglerDim(Tu,Tg,Ks,p_rand);
[beta, tnk, tvk, krk, tnpid, tvpid, krpid, tppid,T] = PidReglerDim(Tu, Tg, Ks, p_rand);

s=tf('s');

% Berechnung der Übertragungsfunktion der Regelung mit PI-Regler, zuerst
% offen, dann geschlossen
uef_regpi_off=krpi*(1+1/(s*tnpi))/(1+s*T(1))/(1+s*T(2));
for k=3:length(T)
   uef_regpi_off=uef_regpi_off/(1+s*T(k));
end
uef_regpi_gesch=uef_regpi_off/(1+uef_regpi_off);

% Berechnung der Übertragungsfunktion der Regelung mit PID-Regler, zuerst
% offen, dann geschlossen
uef_regpid_off=krpid*(1+1/(s*tnpid)+s*tvpid/(1+s*tppid))/(1+s*T(1))/(1+s*T(2));
for k=3:length(T)
   uef_regpid_off=uef_regpid_off/(1+s*T(k));
end
uef_regpid_gesch=uef_regpid_off/(1+uef_regpid_off);


figure
subplot(211)
step(uef_regpi_gesch)
title('Regelung mit PI-Regler')
grid on
subplot(212)
step(uef_regpid_gesch)
t=title('Schrittantwort des geschlossenen Regelkreises mit PID-Regler');
set(t,'FontSize', 15)
x=xlabel('Time');
set(x,'FontSize', 15)
y=ylabel('Amplitude');
set(y, 'FontSize', 15)

grid on
toc
end

