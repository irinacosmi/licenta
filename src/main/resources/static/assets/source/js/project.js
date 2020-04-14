$(document).ready(function () {
  var Result = {
    INVENTIV: 'INVENTIV',
    DIRECTIV: 'DIRECTIV',
    ORGANIZAT: 'ORGANIZAT'
  };

  var sessionId = $('#sessionId').val();
  var result = fetchResult(sessionId);

  var ctx = document.getElementById("myChart");

// Bar chart
  new Chart(ctx, {
    type: 'bar',
    data: {
      labels: [Result.INVENTIV, Result.DIRECTIV, Result.ORGANIZAT],
      datasets: [
        {
          label: "Predictie",
          backgroundColor: ["#3e95cd", "#8e5ea2","#3cba9f"],
          data: [result.result[Result.INVENTIV], result.result[Result.DIRECTIV], result.result[Result.ORGANIZAT]]
        }
      ]
    },
    options: {
      legend: { display: false },
      title: {
        display: true,
        text: 'Predictie job'
      },
        tooltips: {
            enabled: true,
            mode: 'single',
            callbacks: {
                afterLabel: function(tooltipItems, data) {
                    if (tooltipItems.xLabel == Result.INVENTIV) {
                        return ' Dacă scorul dvs. cel mai mare este „Inventiv”:\n se poate spune despre dvs. că sunteţi un om cu imaginaţie bogată. Trăiţi intens fiecare clipă şi vă \n propuneţi tot felul de experienţe. Curios din fire, nonconformist, provocat de ceea ce se spune \n şi de ceea ce se aude mai nou, riscaţi să-i supăraţi pe cei din jur cu trăsnăile ce vă trec prin cap.\n Inteligent, original în gândire, sesizaţi cu uşurinţă mersul lucrurilor. De regulă, reuşiţi să\n descifraţi esenţa unor probleme în locuri în care alţii nu văd nici măcar umbre.\n\n Prin definiţie sunteţi un învingător. Fiecare realizare nu vă aduce decât satisfacţie şi îndemnul de a\n merge mai departe. Rămâneţi perseverent şi evitaţi pe cât e posibil mânia şefilor. Încercaţi şi înţelegeţi\n şi evitaţi întrebările incomode, sub tirul cărora îi supuneţi.\n\n Profesiile care vi se potrivesc sunt: cercetător ştiinţific, detectiv, psihiatru, meteorolog, notar, avocat,\n actor, artist, muzician, scriitor, arhitect, fotograf, pictor, savant, istoric, lingvist.';
                    }
                    if (tooltipItems.xLabel == Result.DIRECTIV) {
                        return ' Dacă scorul dvs. cel mai mare este „Directiv”:\n sunteţi o persoană perspicace. Capabil să rezolvaţi mai multe probleme în acelaşi timp, nu vă pierdeţi\n uşor cu firea. Responsabil, acţionaţi cu sânge rece şi perseverenţă în tot ceea ce faceţi.\n\n Realist, adaptabil la schimbare, nu daţi uşor înapoi din faţa greutăţilor. Energic, destul de inteligent,\n ca să nu spunem foarte inteligent, impuneţi respect şi disciplină în mediul care vă desfăşuraţi\n activitatea. Stăpân pe propriile forţe, vă asumaţi alegerile pe care le faceţi, fără să vă gândiţi\n la ce ar putea spune alţii. Aveţi suficiente abilităţi pentru a vă face ascultat.\n\n Combativ, hotărât, aprig la mânie, nu vă lăsaţi uşor înfrânt sau abătut din drum. Ştiţi ce vă doriţi şi nu\n vă lăsaţi până nu obţineţi ceea ce v-aţi propus. În unele momente folosiţi forţa şi tacticile dure, fără să\n vă întrebaţi dacă este bine sau rău. Sunteţi mai degrabă un păstor, decât o oaie. Nu trebuie să fiţi\n subestimat. Felul în care vă manifestaţi, vă recomandă pentru funcţii de conducere. Depuneţi efort în\n acest sens, perfecţionaţi-vă şi veţi reuşi cu siguranţă. Succes!\n\n Profesiile care vi se potrivesc sunt: director general, proprietar de firmă, avocat, director de şcoală, paznic,\n învăţător, doctor, reporter la ziar, asistent social, casier, vânzător, terapeut, specialist în domeniul\n orientării profesionale, asistentă medicală.';
                    }
                    if (tooltipItems.xLabel == Result.ORGANIZAT) {
                        return ' Daca scorul dvs. cel mai mare este la „Organizat”:\n sunteţi un bun strateg. Viguros, cu puteri ascunse, deţineţi controlul total asupra informaţiilor, fie\n ele şi simple zvonuri. Abil, vă infiltraţi între necunoscuţi. Serios prin tot ceea ce faceţi, nu acceptaţi\n pierderea de vreme.\n\n În situaţia unor urgenţe, vă impuneţi voinţa în faţa altora şi reuşiţi să vă faceţi ascultat. În general, sunteţi\n apreciat ca fiind şeful din umbră. Înţelept, vocea care vă dictează cum să procedaţi este raţiunea minţii.\n Insensibil pentru unii, prea periculos pentru alţii, nu sunteţi o persoană condamnată la singurătate. Toţi\n sunt cu ochii pe dvs. din variate motive. Acum vă caută numai prietenia! Mâine nu se ştie ce vor mai cere!\n Precaut, constant, nu vă angajaţi uşor în depăşiri. Evitaţi pe cât e de posibil provocările şi atacaţi direct\n atunci când nimeni nu se aşteaptă, adoptând o politică a riscului minim. Vă respectaţi principiile,\n încercând tot ce se poate pentru a învinge. Succesul este mai mult decât o obişnuinţă.\n\nProfesiile care vi se potrivesc sunt: programator, contabil, bancher, bibliotecar, inginer, antrenor, pilot,\n fermier, medic veterinar, tâmplar, pompier.';
                    }
                }
            }
        },
      scales: {
        yAxes: [{
          ticks: {
            beginAtZero: true,
            max: 16
          }
        }]
      }
    }
  });
});

function fetchResult(sessionId) {
  var result = null;
  $.ajax(
      {
        type: "GET",
        url: '/buildDataGraph?sessionId=' + sessionId,
        async: false
      })  .done(function( msg ) {
        result = msg;
      });
  return result;
}




