function [tnpid, tvpid, krpid] = Umrechnung_bk_rk(tvk, tnk, tppid,krk)
% Hier werden die bodekonformen Parameter des PID-Reglers in reglerkonform
% umgerechnet.
%
% Umrechnung -> Reglerkonform
% Variablenbeschreibung:
% krpid     Verstärkungsfaktor des PID-Reglers
% tnpid     Tn des PID-Reglers
% tvpid     Tv des PID-Reglers
% tppid     parasitäre Zeitkonsante des realen PID-Reglers

tnpid = tnk+tvk-tppid;
tvpid = (tnk*tvk)/(tnk+tvk-tppid)-tppid;
krpid = krk*(1+tvk/tnk-tppid/tnk);


end

