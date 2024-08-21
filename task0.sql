SELECT CONCAT(hgenrel.familyname, ' ', hgenrel.name, ' ', hgenrel.middlename) as fio, hgenrel.birthdate, hdep.contactrelationship
FROM hppersongeneric hgen
         INNER JOIN hppersondependant hdep ON hgen.sysid = hdep.hppersongenericsysid
         INNER JOIN hppersongeneric hgenrel ON hgenrel.sysid = hdep.hprelatedpersonsysid
where hgen.personid = 'test';