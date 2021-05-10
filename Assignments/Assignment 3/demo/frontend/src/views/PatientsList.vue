<template>
  <v-card>
    <v-card-title>
      Patients
      <v-spacer></v-spacer>
      <v-text-field
        v-model="search"
        append-icon="mdi-magnify"
        label="Search"
        single-line
        hide-details
      ></v-text-field>
      <v-btn @click="addPatient">Add patient</v-btn>
      <v-btn @click="switchView">Consultations</v-btn>
    </v-card-title>
    <v-data-table
      :headers="headers"
      :items="patients"
      :search="search"
      @click:row="editPatient"
    ></v-data-table>
    <PatientsDialog
      :opened="dialogVisible"
      :patient="selectedPatient"
      @refresh="refreshList"
    ></PatientsDialog>
  </v-card>
</template>

<script>
import api from "../api";
import router from "../router";
import PatientsDialog from "../components/PatientsDialog";
export default {
  name: "PatientsList",
  components: { PatientsDialog },
  data() {
    return {
      patients: [],
      search: "",
      headers: [
        {
          text: "Name",
          align: "start",
          sortable: false,
          value: "name",
        },
        { text: "Card Number", value: "cardNumber" },
        { text: "CNP", value: "cnp" },
        { text: "Birth Date", value: "birthDate" },
        { text: "Address", value: "address" },
      ],
      dialogVisible: false,
      selectedPatient: {},
    };
  },
  methods: {
    editPatient(patient) {
      this.selectedPatient = patient;
      this.dialogVisible = true;
    },
    addPatient() {
      this.dialogVisible = true;
    },
    switchView() {
      router.push("/consultations");
    },
    async refreshList() {
      this.dialogVisible = false;
      this.selectedUser = {};
      this.patients = await api.patients.allUsers();
    },
  },
  created() {
    this.refreshList();
  },
};
</script>
<style scoped></style>
