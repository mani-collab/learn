INSERT INTO PATIENT  (ID, F_NAME, L_NAME, SEX, AGE) VALUES
  ('1', 'John', 'Doe', 'M', '24'),
  ('2', 'Shaun', 'Phillips', 'M', '44'),
  ('3', 'Patricia', 'Jennings', 'F', '34');
INSERT INTO PRESCRIPTION  (PID, MEDICINE_NAME, FORM, QUANTITY, DOSAGE, REFILL,PPR_FK ) VALUES
  ('101', 'Lipitor', 'Tablet', '20', '10','True','1'),
  ('102', 'calcipotriene', 'Tablet', '10', '80','True','1'),
  ('103', 'zipsor', 'Liquid', '1', '60','False','2'),
  ('104', 'oleptro', 'Tablet', '30', '100','True','3');